package rest

import core.entity.Teste
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
        em!!.persist(Teste())
    }

    @GET
    @Produces("application/json")
    open fun get(): List<Teste> {
        val jpql = " from Teste "

        val query = em!!.createQuery(jpql, Teste::class.java)
        return query.resultList
    }

}