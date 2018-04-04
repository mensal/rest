package core.persistence

import core.entity.Automovel
import javax.enterprise.inject.spi.CDI
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Transactional
open class AutomovelDAO {

    @PersistenceContext
    private val em: EntityManager? = null

    open fun obter(): List<Automovel>? {
        val jpql = " select a from Automovel a "
        val query = em!!.createQuery(jpql, Automovel::class.java)

        return query.resultList
    }

    companion object {
        fun instance() = CDI.current().select(AutomovelDAO::class.java).get()!!
    }
}