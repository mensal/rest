package rest

import core.entity.Versionado
import core.persistence.CrudDAO
import rest.data.ReqData
import rest.data.ResData
import rest.util.RESTUtil
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.*

abstract class CrudREST<E : Versionado, Q : ReqData<E>, S : ResData<E>, A : CrudDAO<E>> {

    protected abstract fun newEntity(): E

    protected abstract fun newRequestData(): Q

    protected abstract fun newResponseData(): S

    protected abstract var dao: A

    @GET
    @Produces("application/json")
    open fun pesquisar(): List<S>? {
        val resultado = dao.pesquisar().map {
            val data = newResponseData()
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
        val entidade = newEntity()
        data.escreverEm(entidade)
        dao.inserir(entidade)

        val location = uriInfo.requestUriBuilder.path("${entidade.id}").build()
        val responseData = newResponseData()
        responseData.preencherCom(entidade)

        return Response.created(location).entity(responseData).build()
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    open fun obter(@PathParam("id") id: UUID): Response {
        var persistido = carregar(id)
        val resultado = newResponseData()
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
        var builder = RESTUtil.buildIfModified(request, headers, persistido)

        if (builder == null) {
            data.escreverEm(persistido)
            persistido = dao.atualizar(persistido)

            val responseData = newResponseData()
            responseData.preencherCom(persistido)

            val atualizadoEm = Date.from(persistido.atualizadoEm!!.toInstant())
            builder = Response.ok().entity(responseData).lastModified(atualizadoEm)
        }

        return builder!!.build()

    }

    private fun carregar(id: UUID) = dao.obter(id) ?: throw NotFoundException()
}