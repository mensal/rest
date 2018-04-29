package rest.service

import core.entity.Versionado
import core.persistence.CrudDAO
import core.util.Reflections
import org.apache.commons.lang.time.DateUtils
import rest.PreconditionFailedException
import rest.UnprocessableEntityException
import rest.data.ReqData
import rest.data.ResData
import rest.security.Logado
import java.util.*
import javax.enterprise.inject.spi.CDI
import javax.transaction.Transactional
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.*
import javax.ws.rs.core.*
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.superclasses

abstract class CrudREST<ENT : Versionado, REQ : ReqData<ENT>, out RES : ResData<ENT>, out DAO : CrudDAO<ENT>> {

    protected open val dao: DAO
        get() = CDI.current().select(Reflections.argument<DAO>(this, CrudREST::class, 3).java).get()!!

    protected open fun novaEntidade() = Reflections.argument<ENT>(this, CrudREST::class, 0).createInstance()

    protected open fun novoRequestData() = Reflections.argument<REQ>(this, CrudREST::class, 1).createInstance()

    protected open fun novoResponseData() = Reflections.argument<RES>(this, CrudREST::class, 2).createInstance()

    protected open fun antesDePersistir(entidade: ENT, requestData: REQ) {}

    protected open fun depoisDePersistir(entidade: ENT, requestData: REQ) {}

    protected open fun antesDeExcluir(entidade: ENT) {}

    protected open fun depoisDePesquisar(entidade: ENT) = entidade

    protected open fun depoisDePesquisar(entidades: List<ENT>) = entidades

    protected open val violationException = UnprocessableEntityException()

    @GET
    @Logado
    @Produces("application/json")
    open fun pesquisar(@NotNull @QueryParam("ano") ano: Int, @NotNull @QueryParam("mes") mes: Int): List<RES>? {
        var persistidos = dao.pesquisar(ano, mes)
        persistidos = depoisDePesquisar(persistidos)


        print("cccc")
        print(this::class.superclasses[0].simpleName)

        val resultado = persistidos.map {
            val entidade = depoisDePesquisar(it)

            val data = novoResponseData()
            data.preencherCom(entidade)
            data
        }

        return if (resultado.isEmpty()) null else resultado
    }

    @POST
    @Logado
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional(rollbackOn = [Throwable::class])
    open fun inserir(@Valid data: REQ, @Context uriInfo: UriInfo): Response {
        val entidade = novaEntidade()
        data.escreverEm(entidade)

        antesDePersistir(entidade, data)
        dao.inserir(entidade)
        depoisDePersistir(entidade, data)
        lancarExcecaoSeNecessario()

        val location = uriInfo.requestUriBuilder.path("${entidade.id}").build()
        val responseData = novoResponseData()
        responseData.preencherCom(entidade)

        val atualizadoEm = Date.from(entidade.atualizadoEm!!.toInstant())
        return Response.created(location).entity(responseData).lastModified(atualizadoEm).build()
    }

    @GET
    @Logado
    @Path("{id}")
    @Produces("application/json")
    open fun obter(@PathParam("id") id: UUID): Response {
        var persistido = carregar(id)
        persistido = depoisDePesquisar(persistido)

        val resultado = novoResponseData()
        resultado.preencherCom(persistido)

        val atualizadoEm = Date.from(persistido.atualizadoEm!!.toInstant())
        return Response.ok().entity(resultado).lastModified(atualizadoEm).build()
    }

    @PUT
    @Logado
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional(rollbackOn = [Throwable::class])
    open fun atualizar(@PathParam("id") id: UUID, @Valid data: REQ, @Context headers: HttpHeaders, @Context request: Request): Response {
        var persistido = carregar(id)
        var builder = buildSeModificado(request, headers, persistido)

        if (builder == null) {
            data.escreverEm(persistido)

            antesDePersistir(persistido, data)
            persistido = dao.atualizar(persistido)
            depoisDePersistir(persistido, data)
            lancarExcecaoSeNecessario()

            val responseData = novoResponseData()
            responseData.preencherCom(persistido)

            val atualizadoEm = Date.from(persistido.atualizadoEm!!.toInstant())
            builder = Response.ok().entity(responseData).lastModified(atualizadoEm)
        }

        return builder!!.build()
    }

    @DELETE
    @Logado
    @Path("{id}")
    @Transactional(rollbackOn = [Throwable::class])
    open fun deletar(@PathParam("id") id: UUID) {
        val persistido = carregar(id)

        antesDeExcluir(persistido)
        dao.excluir(persistido)
        lancarExcecaoSeNecessario()
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

    private fun lancarExcecaoSeNecessario() {
        if (!violationException.violations.isEmpty()) throw violationException
    }
}