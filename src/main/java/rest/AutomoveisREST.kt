package rest

import core.entity.Automovel
import core.persistence.AutomovelDAO
import rest.data.AutomovelData
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.validation.Valid
import javax.ws.rs.*

@Path("automoveis")
internal open class AutomoveisREST {

    @PersistenceContext
    private val em: EntityManager? = null

    @GET
    @Produces("application/json")
    open fun obter(): List<Automovel> = AutomovelDAO.instance().obter().orEmpty()

    @POST
    @Consumes("application/json")
    open fun inserir(@Valid data: AutomovelData) {
        println("${data.nome}")
    }
}