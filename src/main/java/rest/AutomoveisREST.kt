package rest

import core.entity.Automovel
import core.entity.Periodo
import core.persistence.AutomovelDAO
import rest.data.AutomovelData
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@Path("automoveis")
internal open class AutomoveisREST {

    @GET
    @Produces("application/json")
    open fun pesquisar(): List<AutomovelData>? {
        val resultado = AutomovelDAO.instance().pesquisar().map { AutomovelData().ler(it) }

        return if (resultado.isEmpty()) null else resultado
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    open fun obter(@PathParam("id") id: UUID) = AutomovelData().ler(carregar(id))

    @POST
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun inserir(@Valid data: AutomovelData, @Context uriInfo: UriInfo): Response {
        val automovel = data.escrever(Automovel())!!

        if (data.periodo == null) automovel.periodo = Periodo()
        if (data.periodo?.de == null) automovel.periodo?.de = Date()

        AutomovelDAO.instance().inserir(automovel)

        val location = uriInfo.requestUriBuilder.path("${automovel.id}").build()
        val entity = AutomovelData().ler(automovel)

        return Response.created(location).entity(entity).build()
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun atualizar(@PathParam("id") id: UUID, @Valid data: AutomovelData): AutomovelData {
        data.id = id

        val persistido = data.escrever(carregar(id))!!
        AutomovelDAO.instance().atualizar(persistido)

        return AutomovelData().ler(persistido)
    }


    private fun carregar(id: UUID): Automovel = AutomovelDAO.instance().obter(id) ?: throw NotFoundException()
}