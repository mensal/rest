package core.persistence

import core.entity.TipoDespesaDiversa
import java.util.*
import javax.enterprise.inject.spi.CDI
import javax.persistence.EntityManager
import javax.persistence.NoResultException
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Transactional
open class TipoDiversaDAO {

    @PersistenceContext
    private lateinit var em: EntityManager

    open fun obter(id: UUID): TipoDespesaDiversa? {
        val jpql = " select t from TipoDespesaDiversa t where t.id = :id "
        val query = em.createQuery(jpql, TipoDespesaDiversa::class.java)
        query.setParameter("id", id)

        var resultado: TipoDespesaDiversa?
        try {
            resultado = query.singleResult
        } catch (cause: NoResultException) {
            resultado = null
        }

        return resultado
    }

    open fun pesquisar(): List<TipoDespesaDiversa> {
        val jpql = " select t from TipoDespesaDiversa t "
        val query = em.createQuery(jpql, TipoDespesaDiversa::class.java)

        return query.resultList
    }

    open fun inserir(despesa: TipoDespesaDiversa) = em.persist(despesa)

    open fun atualizar(despesa: TipoDespesaDiversa) = em.merge(despesa)!!

    companion object {
        fun instance() = CDI.current().select(TipoDiversaDAO::class.java).get()!!
    }
}