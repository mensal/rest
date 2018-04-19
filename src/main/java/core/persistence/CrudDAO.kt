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

    open fun obter(id: UUID): E? = em.find(entityClass, id)

//    protected open var pesquisarOrderBy = ""

    protected open fun pesquisarWhere(ano: Int, mes: Int) = ""

    protected open fun pesquisarOrderBy(ano: Int, mes: Int) = ""

    open fun pesquisar(ano: Int, mes: Int): List<E> {
        val pesquisarWhere = pesquisarWhere(ano, mes)
        val pesquisarOrderBy = pesquisarOrderBy(ano, mes)

        val jpql = " select e from ${entityClass.name} e" + (if (!isEmpty(pesquisarWhere)) " where $pesquisarWhere" else "") + (if (!isEmpty(pesquisarOrderBy)) " order by $pesquisarOrderBy" else "")
        val query = em.createQuery(jpql, entityClass)

        return query.resultList
    }

    open fun inserir(entidade: E) = em.persist(entidade)

    open fun atualizar(entidade: E) = em.merge(entidade)!!

    open fun excluir(entidade: E) = em.remove(entidade)

    protected abstract val entityClass: Class<E>
}