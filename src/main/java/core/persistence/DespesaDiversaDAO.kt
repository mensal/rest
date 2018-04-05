package core.persistence

import core.entity.DespesaDiversa
import java.util.*
import javax.enterprise.inject.spi.CDI
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Transactional
open class DespesaDiversaDAO {

    @PersistenceContext
    private lateinit var em: EntityManager

    open fun obter(id: UUID) = em.find(DespesaDiversa::class.java, id)

    open fun pesquisar(): List<DespesaDiversa> {
        val jpql = " select dd from DespesaDiversa dd "
        val query = em.createQuery(jpql, DespesaDiversa::class.java)

        return query.resultList
    }

    open fun inserir(despesa: DespesaDiversa) = em.persist(despesa)

    open fun atualizar(despesa: DespesaDiversa) = em.merge(despesa)!!

    companion object {
        fun instance() = CDI.current().select(DespesaDiversaDAO::class.java).get()!!
    }
}