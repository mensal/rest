package rest

import core.entity.Periodo
import core.entity.TipoDiversa
import core.persistence.TipoDiversaDAO
import rest.data.TipoDiversaData
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@Path("tipo/diversas")
internal open class TipoDiversasREST {

    private val dao by lazy { TipoDiversaDAO.instance() }

    @GET
    @Produces("application/json")
    open fun pesquisar(): List<TipoDiversaData>? {
        val resultado = dao.pesquisar().map { TipoDiversaData().ler(it) }
        return if (resultado.isEmpty()) null else resultado
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    open fun obter(@PathParam("id") id: UUID) = TipoDiversaData().ler(carregar(id))

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

    @PUT
    @Path("{id}")
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun atualizar(@PathParam("id") id: UUID, @Valid data: TipoDiversaData): TipoDiversaData {
        data.id = id

        val persistido = data.escrever(carregar(id))!!
        dao.atualizar(persistido)

        return TipoDiversaData().ler(persistido)
    }

    private fun carregar(id: UUID) = dao.obter(id) ?: throw NotFoundException()
}