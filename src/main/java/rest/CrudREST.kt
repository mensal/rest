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

abstract class CrudREST<E : Versionado, Q : ReqData<E>, S : ResData<E, S>, A : CrudDAO<E>> {

    protected abstract fun newEntity(): E

    protected abstract fun newRequestData(): Q

    protected abstract fun newResponseData(): S

    protected abstract var dao: A

    @GET
    @Produces("application/json")
    open fun pesquisar(): List<S>? {
        val resultado = dao.pesquisar().map { newResponseData().ler(it) }
        return if (resultado.isEmpty()) null else resultado

    }

    @POST
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun inserir(@Valid data: Q, @Context uriInfo: UriInfo): Response {
        val entidade = data.escrever(newEntity())!!
        dao.inserir(entidade)

        val location = uriInfo.requestUriBuilder.path("${entidade.id}").build()
        return Response.created(location).entity(newResponseData().ler(entidade)).build()
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    open fun obter(@PathParam("id") id: UUID): Response {
        var persistido = carregar(id)
        val resultado = newResponseData().ler(persistido)

        return Response.ok().entity(resultado).lastModified(persistido.atualizadoEm).build()

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
//            data.id = id
            persistido = dao.atualizar(data.escrever(persistido)!!)
            builder = Response.ok().entity(newResponseData().ler(persistido)).lastModified(persistido.atualizadoEm)
        }

        return builder.build()

    }

    private fun carregar(id: UUID) = dao.obter(id) ?: throw NotFoundException()
}