package rest

import core.entity.Pagamento
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("teste")
internal open class TesteREST {

    @PersistenceContext
    private val em: EntityManager? = null

    @POST
    @Transactional
    open fun post() {
        val lancamento = Pagamento()
        lancamento.data = Date()

        em!!.persist(lancamento)
    }

    @GET
    @Produces("application/json")
    open fun get(): List<Pagamento> {
        val jpql = " select l from Pagamento l "

        val query = em!!.createQuery(jpql, Pagamento::class.java)
        return query.resultList
    }
}