package rest

import core.entity.Periodo
import core.entity.TipoDiversa
import core.persistence.TipoDiversaDAO
import rest.data.TipoDiversaData
import rest.util.RESTUtil.Companion.buildIfModified
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.*


@Path("tipo/diversas")
internal open class TipoDiversasREST {

    private val dao by lazy { TipoDiversaDAO.instance() }

    @GET
    @Produces("application/json")
    open fun pesquisar(): List<TipoDiversaData>? {
        val resultado = dao.pesquisar().map { TipoDiversaData().ler(it) }
        return if (resultado.isEmpty()) null else resultado
    }

    @POST
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun inserir(@Valid data: TipoDiversaData, @Context uriInfo: UriInfo): Response {
        val tipo = data.escrever(TipoDiversa())!!

        if (data.periodo == null) tipo.periodo = Periodo()
        if (data.periodo?.de == null) tipo.periodo?.de = Date()

        dao.inserir(tipo)

        val location = uriInfo.requestUriBuilder.path("${tipo.id}").build()
        val entity = TipoDiversaData().ler(tipo)

        return Response.created(location).entity(entity).build()
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    open fun obter(@PathParam("id") id: UUID): Response {
        var persistido = carregar(id)
        val resultado = TipoDiversaData().ler(persistido)

        return Response.ok().entity(resultado).lastModified(persistido.atualizadoEm).build()
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun atualizar(@PathParam("id") id: UUID, @Valid data: TipoDiversaData, @Context headers: HttpHeaders, @Context request: Request): Response {
        var persistido = carregar(id)
        var builder = buildIfModified(request, headers, persistido)

        if (builder == null) {
            data.id = id
            persistido = dao.atualizar(data.escrever(persistido)!!)
            builder = Response.ok().entity(TipoDiversaData().ler(persistido)).lastModified(persistido.atualizadoEm)
        }

        return builder.build()
    }

    private fun carregar(id: UUID) = dao.obter(id) ?: throw NotFoundException()
}