package rest

import core.entity.Versionado
import core.persistence.CrudDAO
import rest.data.Data
import rest.util.RESTUtil
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.*

abstract class CrudREST<E : Versionado, D : Data<E, D>, A : CrudDAO<E>> {

    protected abstract fun newEntity(): E

    protected abstract fun newData(): D

    protected abstract var dao: A

    @GET
    @Produces("application/json")
    open fun pesquisar(): List<D>? {
        val resultado = dao.pesquisar().map { newData().ler(it) }
        return if (resultado.isEmpty()) null else resultado

    }

    @POST
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun inserir(@Valid data: D, @Context uriInfo: UriInfo, antesDeInserir: ((E) -> Unit)?): Response {
        val entidade = data.escrever(newEntity())!!

        antesDeInserir?.invoke(entidade)

//        if (data.periodo == null) entidade.periodo = Periodo()
//        if (data.periodo?.de == null) entidade.periodo?.de = Date()

        dao.inserir(entidade)

        val location = uriInfo.requestUriBuilder.path("${entidade.id}").build()
        return Response.created(location).entity(newData().ler(entidade)).build()
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    open fun obter(@PathParam("id") id: UUID): Response {
        var persistido = carregar(id)
        val resultado = newData().ler(persistido)

        return Response.ok().entity(resultado).lastModified(persistido.atualizadoEm).build()

    }

    @PUT
    @Path("{id}")
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun atualizar(@PathParam("id") id: UUID, @Valid data: D, @Context headers: HttpHeaders, @Context request: Request): Response {
        var persistido = carregar(id)
        var builder = RESTUtil.buildIfModified(request, headers, persistido)

        if (builder == null) {
//            data.id = id
            persistido = dao.atualizar(data.escrever(persistido)!!)
            builder = Response.ok().entity(newData().ler(persistido)).lastModified(persistido.atualizadoEm)
        }

        return builder.build()

    }

    private fun carregar(id: UUID) = dao.obter(id) ?: throw NotFoundException()
}