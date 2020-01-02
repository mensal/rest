package core.persistence

import core.entity.Versionado
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.TypedQuery
import kotlin.reflect.KClass

typealias PrepareQuery<E> = (TypedQuery<E>) -> Unit

interface CrudDAO<E : Any> {

    companion object {
        fun <E : Versionado> pesquisar(type: KClass<E>, em: EntityManager, where: String = "", orderBy: String = "", prepare: PrepareQuery<E> = {}): List<E> {
            val ql = "from ${type.java.canonicalName} ${if (where.isNotBlank()) "where $where" else ""} order by ${if (orderBy.isNotBlank()) "$orderBy, " else ""} id asc"

            val query = em.createQuery(ql, type.java)
            prepare.invoke(query)

            return query.resultList
        }

        fun <E : Versionado> obter(id: UUID, type: KClass<E>, em: EntityManager): E? = em.find(type.java, id)
        fun <E : Versionado> inserir(entidade: E, em: EntityManager) = em.persist(entidade)
        fun <E : Versionado> atualizar(entidade: E, em: EntityManager): E = em.merge(entidade)
        fun <E : Versionado> excluir(entidade: E, em: EntityManager) = em.remove(entidade)
    }

    fun obter(id: UUID): E?
    fun pesquisar(params: Map<String, String> = emptyMap()): List<E>
    fun inserir(entidade: E)
    fun atualizar(entidade: E): E
    fun excluir(entidade: E)
}