package app.core.persistence

import app.core.util.Reflections
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery
import kotlin.reflect.KClass

@Transactional
abstract class CrudDAO<E : Any> {

    protected val entityClass: KClass<E>
        get() = Reflections.argument(this, CrudDAO::class, 0)

    @PersistenceContext
    protected lateinit var em: EntityManager

    fun obter(id: UUID): E? = em.find(entityClass.java, id)

    protected fun pesquisarWhere(params: Map<String, String>): String = ""

    protected fun pesquisarOrderBy(params: Map<String, String>): String = ""

    protected fun antesDePesquisar(params: Map<String, String>, query: TypedQuery<E>) {}

    fun pesquisar(params: Map<String, String> = emptyMap()): List<E> {
        val pesquisarWhere = pesquisarWhere(params)
        val pesquisarOrderBy = pesquisarOrderBy(params)

        val strPesquisarWhere = if (pesquisarWhere.isNotBlank()) "where $pesquisarWhere" else ""
        val strPesquisarOrderBy = if (pesquisarOrderBy.isNotBlank()) "order by $pesquisarOrderBy" else ""

        val jpql = "from ${entityClass.qualifiedName} $strPesquisarWhere $strPesquisarOrderBy"
        val query = em.createQuery(jpql, entityClass.java)
        antesDePesquisar(params, query)

        return query.resultList
    }

    fun inserir(entidade: E) = em.persist(entidade)

    fun atualizar(entidade: E) = em.merge(entidade)!!

    fun excluir(entidade: E) = em.remove(entidade)
}