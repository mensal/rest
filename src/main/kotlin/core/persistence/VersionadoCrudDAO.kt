package core.persistence

import core.entity.Versionado
import java.time.ZonedDateTime
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.NoResultException
import javax.persistence.TypedQuery
import kotlin.reflect.KClass

abstract class VersionadoCrudDAO<V : Versionado> : CrudDAO<V>() {
    companion object {
        open fun <E : Versionado> pesquisar2(params: Map<String, String> = emptyMap(), type: KClass<E>, em: EntityManager): List<E> {
            var ql = StringBuffer()

            ql.append(" select e from ${type.java.canonicalName} u where 1 = 1 ")

            params["atualizado_apos"]?.let { ql.append(" and atualizadoEm > :atualizadoApos ") }
            params["mostrar_excluidos"]?.let { if (!it.toBoolean()) ql.append(" and excluidoEm is null ") }

            ql.append(" order by atualizadoEm asc ")

            val query = em.createQuery(ql.toString(), type.java)

            params["atualizado_apos"]?.let { query.setParameter("atualizadoApos", ZonedDateTime.parse(it)) }

            return query.resultList
        }

        open fun <E : Versionado> obter2(id: UUID, type: KClass<E>, em: EntityManager): E? = em.find(type.java, id)

        open fun <E : Versionado> inserir2(entidade: E, em: EntityManager) = em.persist(entidade)

        open fun <E : Versionado> atualizar2(entidade: E, em: EntityManager) = em.merge(entidade)

        open fun <E : Versionado> excluir2(entidade: E, em: EntityManager) {
            entidade.excluidoEm = ZonedDateTime.now()
            entidade.atualizadoEm = entidade.excluidoEm
        }
    }

    override fun pesquisarWhere(params: Map<String, String>): String {
        var criterios = mutableListOf<String>()
        super.pesquisarWhere(params).let { if (it.isNotBlank()) criterios.add(it) }

        if (params.containsKey("atualizado_apos")) {
            criterios.add("atualizadoEm > :atualizado_apos")
        }

        if (!params.containsKey("mostrar_excluidos") || !params["mostrar_excluidos"]!!.toBoolean()) {
            criterios.add("excluidoEm is null")
        }

        return criterios.let { if (it.isEmpty()) "" else criterios.joinToString(" and ") }
    }

    override fun antesDePesquisar(params: Map<String, String>, query: TypedQuery<V>) {
        super.antesDePesquisar(params, query)

        if (params.containsKey("atualizado_apos")) {
            query.setParameter("atualizado_apos", ZonedDateTime.parse(params["atualizado_apos"]!!))
        }
    }

    override fun pesquisarOrderBy(params: Map<String, String>) = "atualizadoEm asc"

    override fun obter(id: UUID): V? {
        val jpql = "from ${entityClass.qualifiedName} where id = :id and excluidoEm is null"
        val query = em.createQuery(jpql, entityClass.java)
        query.setParameter("id", id)

        return try {
            query.singleResult
        } catch (e: NoResultException) {
            null
        }
    }

    override fun excluir(entidade: V) {
        entidade.excluidoEm = ZonedDateTime.now()
        entidade.atualizadoEm = entidade.excluidoEm
    }
}