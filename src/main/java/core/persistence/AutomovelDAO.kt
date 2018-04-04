package core.persistence

import core.entity.Automovel
import java.util.*
import javax.enterprise.inject.spi.CDI
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Transactional
open class AutomovelDAO {

    @PersistenceContext
    private val em: EntityManager? = null

    open fun obter(id: UUID) = em!!.find(Automovel::class.java, id)

    open fun pesquisar(): List<Automovel> {
        val jpql = " select a from Automovel a "
        val query = em!!.createQuery(jpql, Automovel::class.java)

        return query.resultList
    }

    open fun inserir(automovel: Automovel) = em!!.persist(automovel)

    open fun atualizar(automovel: Automovel) = em!!.merge(automovel)

    companion object {
        fun instance() = CDI.current().select(AutomovelDAO::class.java).get()!!
    }
}