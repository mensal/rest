package app.core.persistence

import app.core.util.Reflections
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery
import javax.transaction.Transactional
import kotlin.reflect.KClass

@Transactional
abstract class CrudDAO<E : Any> {

    protected open val entityClass: KClass<E>
        get() = Reflections.argument(this, CrudDAO::class, 0)

    @PersistenceContext
    protected open lateinit var em: EntityManager

    open fun obter(id: UUID): E? = em.find(entityClass.java, id)

    protected open fun pesquisarWhere(params: Map<String, String>): String = ""

    protected open fun pesquisarOrderBy(params: Map<String, String>): String = ""

    protected open fun antesDePesquisar(params: Map<String, String>, query: TypedQuery<E>) {}

    open fun pesquisar(params: Map<String, String> = emptyMap()): List<E> {
        val pesquisarWhere = pesquisarWhere(params)
        val pesquisarOrderBy = pesquisarOrderBy(params)

        val strPesquisarWhere = if (pesquisarWhere.isNotBlank()) "where $pesquisarWhere" else ""
        val strPesquisarOrderBy = if (pesquisarOrderBy.isNotBlank()) "order by $pesquisarOrderBy" else ""

        val jpql = "from ${entityClass.qualifiedName} $strPesquisarWhere $strPesquisarOrderBy"
        val query = em.createQuery(jpql, entityClass.java)
        antesDePesquisar(params, query)

        return query.resultList
    }

    open fun inserir(entidade: E) = em.persist(entidade)

    open fun atualizar(entidade: E) = em.merge(entidade)!!

    open fun excluir(entidade: E) = em.remove(entidade)
}