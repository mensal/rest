package rest

import core.entity.Versionado
import core.persistence.CrudDAO
import org.apache.commons.lang3.time.DateUtils
import rest.data.ReqData
import rest.data.ResData
import rest.util.PreconditionFailedException
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.*

abstract class CrudREST<E : Versionado, Q : ReqData<E>, out S : ResData<E>, A : CrudDAO<E>> {

    protected abstract fun novaEntidade(): E

    protected abstract fun novoRequestData(): Q

    protected abstract fun novoResponseData(): S

    protected abstract var dao: A

    @GET
    @Produces("application/json")
    open fun pesquisar(): List<S>? {
        val resultado = dao.pesquisar().map {
            val data = novoResponseData()
            data.preencherCom(it)
            data
        }

        return if (resultado.isEmpty()) null else resultado

    }

    @POST
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun inserir(@Valid data: Q, @Context uriInfo: UriInfo): Response {
        val entidade = novaEntidade()
        data.escreverEm(entidade)

        antesDePersistir(entidade, data)
        dao.inserir(entidade)

        val location = uriInfo.requestUriBuilder.path("${entidade.id}").build()
        val responseData = novoResponseData()
        responseData.preencherCom(entidade)

        return Response.created(location).entity(responseData).build()
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    open fun obter(@PathParam("id") id: UUID): Response {
        var persistido = carregar(id)
        val resultado = novoResponseData()
        resultado.preencherCom(persistido)

        val atualizadoEm = Date.from(persistido.atualizadoEm!!.toInstant())
        return Response.ok().entity(resultado).lastModified(atualizadoEm).build()
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun atualizar(@PathParam("id") id: UUID, @Valid data: Q, @Context headers: HttpHeaders, @Context request: Request): Response {
        var persistido = carregar(id)
        var builder = buildSeModificado(request, headers, persistido)

        if (builder == null) {
            data.escreverEm(persistido)

            antesDePersistir(persistido, data)
            persistido = dao.atualizar(persistido)

            val responseData = novoResponseData()
            responseData.preencherCom(persistido)

            val atualizadoEm = Date.from(persistido.atualizadoEm!!.toInstant())
            builder = Response.ok().entity(responseData).lastModified(atualizadoEm)
        }

        return builder!!.build()

    }

    private fun carregar(id: UUID) = dao.obter(id) ?: throw NotFoundException()

    private fun buildSeModificado(request: Request, headers: HttpHeaders, versionado: Versionado): Response.ResponseBuilder? {
        headers.getHeaderString("If-Unmodified-Since") ?: throw PreconditionFailedException()

        return try {
            val atualizadoEm = Date.from(versionado.atualizadoEm!!.toInstant())
            request.evaluatePreconditions(DateUtils.truncate(atualizadoEm, Calendar.SECOND))
        } catch (cause: Exception) {
            cause.printStackTrace()
            throw PreconditionFailedException()
        }
    }

    protected abstract fun antesDePersistir(entidade: E, dataRequest: Q)
}