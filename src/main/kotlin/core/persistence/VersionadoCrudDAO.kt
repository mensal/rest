package core.persistence

import core.entity.Versionado
import java.time.ZonedDateTime
import java.util.*
import javax.persistence.NoResultException
import javax.persistence.TypedQuery

abstract class VersionadoCrudDAO<V : Versionado> : CrudDAO<V>() {

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