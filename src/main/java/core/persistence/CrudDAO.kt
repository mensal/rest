package core.persistence

import org.apache.commons.lang.StringUtils.isEmpty
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Transactional
abstract class CrudDAO<E> {

    @PersistenceContext
    protected open lateinit var em: EntityManager

    protected open var orderBy: String = ""

    open fun obter(id: UUID): E? = em.find(entityClass, id)

    open fun pesquisar(): List<E> {
        val jpql = " select e from ${entityClass.name} e " + if (!isEmpty(orderBy)) "order by $orderBy" else ""
        val query = em.createQuery(jpql, entityClass)

        return query.resultList
    }

    open fun inserir(entidade: E) = em.persist(entidade)

    open fun atualizar(entidade: E) = em.merge(entidade)!!

    open fun excluir(entidade: E) = em.remove(entidade)

    protected abstract val entityClass: Class<E>
}