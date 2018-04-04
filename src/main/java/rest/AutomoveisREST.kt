package rest

import core.entity.Periodo
import core.persistence.AutomovelDAO
import rest.data.AutomovelData
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@Path("automoveis")
internal open class AutomoveisREST {

    @PersistenceContext
    private val em: EntityManager? = null

    @GET
    @Produces("application/json")
    open fun obter(): List<AutomovelData>? {
        var resultado = mutableListOf<AutomovelData>()

        AutomovelDAO.instance().pesquisar().orEmpty().forEach {
            resultado.add(AutomovelData().preencher(it))
        }

        return if (resultado.isEmpty()) null else resultado
    }

    @POST
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun inserir(@Valid data: AutomovelData, @Context uriInfo: UriInfo): Response {
        val automovel = data.converter()

        if (data.periodo == null) automovel.periodo = Periodo()
        if (data.periodo?.de == null) automovel.periodo?.de = Date()

        AutomovelDAO.instance().inserir(automovel)

        val location = uriInfo.requestUriBuilder.path("${automovel.id}").build()
        val entity = AutomovelData().preencher(automovel)

        return Response.created(location).entity(entity).build()
    }
}