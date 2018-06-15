package core.persistence

import core.util.Reflections
import org.apache.commons.lang.StringUtils.isEmpty
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional
import javax.ws.rs.core.MultivaluedMap
import kotlin.reflect.KClass

@Transactional
abstract class CrudDAO<E : Any> {

    protected open val entityClass: KClass<E>
        get() = Reflections.argument(this, CrudDAO::class, 0)

    @PersistenceContext
    protected open lateinit var em: EntityManager

    open fun obter(id: UUID): E? = em.find(entityClass.java, id)

    protected open fun pesquisarWhere(params: MultivaluedMap<String, String>) = ""

    protected open fun pesquisarOrderBy(params: MultivaluedMap<String, String>) = ""

    //    open fun pesquisar(ano: Int, mes: Int): List<E> {
    open fun pesquisar(params: MultivaluedMap<String, String>): List<E> {
        val pesquisarWhere = pesquisarWhere(params)
//        val pesquisarWhere = pesquisarWhere(ano, mes)
        val pesquisarOrderBy = pesquisarOrderBy(params)
//        val pesquisarOrderBy = pesquisarOrderBy(ano, mes)

        val jpql = " select e from ${entityClass.qualifiedName} e" + (if (!isEmpty(pesquisarWhere)) " where $pesquisarWhere" else "") + (if (!isEmpty(pesquisarOrderBy)) " order by $pesquisarOrderBy" else "")
        val query = em.createQuery(jpql, entityClass.java)

        return query.resultList
    }

    open fun inserir(entidade: E) = em.persist(entidade)

    open fun atualizar(entidade: E) = em.merge(entidade)!!

    open fun excluir(entidade: E) = em.remove(entidade)
}