package core.persistence

import core.entity.TipoDespesaAutomovel
import java.util.*
import javax.enterprise.inject.spi.CDI
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Transactional
open class AutomovelDAO {

    @PersistenceContext
    private lateinit var em: EntityManager

    open fun obter(id: UUID) = em.find(TipoDespesaAutomovel::class.java, id)

    open fun pesquisar(): List<TipoDespesaAutomovel> {
        val jpql = " select a from TipoDespesaAutomovel a "
        val query = em.createQuery(jpql, TipoDespesaAutomovel::class.java)

        return query.resultList
    }

    companion object {
        fun instance() = CDI.current().select(AutomovelDAO::class.java).get()!!
    }
}