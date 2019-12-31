package core.persistence

import core.entity.Versionado
import java.util.*
import javax.persistence.EntityManager
import kotlin.reflect.KClass

interface CrudDAO<E : Any> {

    companion object {
        fun <E : Versionado> pesquisar(params: Map<String, String> = emptyMap(), type: KClass<E>, em: EntityManager): List<E> {
            val ql = StringBuffer()

            ql.append(" from ${type.java.canonicalName} where 1 = 1 ")
            ql.append(" order by id asc ")

            val query = em.createQuery(ql.toString(), type.java)
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