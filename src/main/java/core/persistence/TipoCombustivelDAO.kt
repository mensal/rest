package core.persistence

import core.entity.TipoCombustivel
import java.util.*
import javax.enterprise.inject.spi.CDI
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Transactional
open class TipoCombustivelDAO {

    @PersistenceContext
    private lateinit var em: EntityManager

    open fun obter(id: UUID) = em.find(TipoCombustivel::class.java, id)

    open fun pesquisar(): List<TipoCombustivel> {
        val jpql = " select t from TipoCombustivel t "
        val query = em.createQuery(jpql, TipoCombustivel::class.java)

        return query.resultList
    }

    companion object {
        fun instance() = CDI.current().select(TipoCombustivelDAO::class.java).get()!!
    }
}