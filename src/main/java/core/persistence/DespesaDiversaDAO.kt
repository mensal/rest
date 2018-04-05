package core.persistence

import core.entity.TipoDespesaDiversa
import java.util.*
import javax.enterprise.inject.spi.CDI
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Transactional
open class DespesaDiversaDAO {

    @PersistenceContext
    private lateinit var em: EntityManager

    open fun obter(id: UUID) = em.find(TipoDespesaDiversa::class.java, id)

    open fun pesquisar(): List<TipoDespesaDiversa> {
        val jpql = " select dd from TipoDespesaDiversa dd "
        val query = em.createQuery(jpql, TipoDespesaDiversa::class.java)

        return query.resultList
    }

    open fun inserir(despesa: TipoDespesaDiversa) = em.persist(despesa)

    open fun atualizar(despesa: TipoDespesaDiversa) = em.merge(despesa)!!

    companion object {
        fun instance() = CDI.current().select(DespesaDiversaDAO::class.java).get()!!
    }
}