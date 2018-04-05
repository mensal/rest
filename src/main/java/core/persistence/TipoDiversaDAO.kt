package core.persistence

import core.entity.TipoDiversa
import java.util.*
import javax.enterprise.inject.spi.CDI
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Transactional
open class TipoDiversaDAO {

    @PersistenceContext
    private lateinit var em: EntityManager

    open fun obter(id: UUID) = em.find(TipoDiversa::class.java, id)

    open fun pesquisar(): List<TipoDiversa> {
        val jpql = " select t from TipoDiversa t "
        val query = em.createQuery(jpql, TipoDiversa::class.java)

        return query.resultList
    }

    open fun inserir(despesa: TipoDiversa) = em.persist(despesa)

    open fun atualizar(despesa: TipoDiversa) = em.merge(despesa)!!

    companion object {
        fun instance() = CDI.current().select(TipoDiversaDAO::class.java).get()!!
    }
}