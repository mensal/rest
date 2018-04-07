package core.persistence

import java.util.*
import javax.persistence.EntityManager
import javax.persistence.NoResultException
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Transactional
abstract class CrudDAO<E> {

    @PersistenceContext
    private lateinit var em: EntityManager

    open fun obter(id: UUID): E? {
        val jpql = " select e from ${entityClass.simpleName} e where e.id = :id "
        val query = em.createQuery(jpql, entityClass)
        query.setParameter("id", id)

        return try {
            query.singleResult
        } catch (cause: NoResultException) {
            null
        }
    }

    open fun pesquisar(): List<E> {
        val jpql = " select e from ${entityClass.simpleName} e "
        val query = em.createQuery(jpql, entityClass)

        return query.resultList
    }

    open fun inserir(despesa: E) = em.persist(despesa)

    open fun atualizar(despesa: E) = em.merge(despesa)!!

    protected abstract val entityClass: Class<E>
}