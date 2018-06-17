package core.persistence

import core.entity.Versionado
import java.time.ZonedDateTime
import javax.persistence.TypedQuery
import javax.transaction.Transactional

@Transactional
abstract class VersionadoCrudDAO<V : Versionado> : CrudDAO<V>() {

    override fun pesquisarWhere(params: Map<String, String>): String {
        var criterios = mutableListOf<String>()
        super.pesquisarWhere(params).let { if (it.isNotBlank()) criterios.add(it) }

        if (params.containsKey("atualizado_apos")) {
            criterios.add("atualizado_em > :atualizado_apos")
        }

        if (!params.containsKey("mostrar_excluidos") || !params["mostrar_excluidos"]!!.toBoolean()) {
            criterios.add("excluido_em is null")
        }

        return criterios.let { if (it.isEmpty()) "" else criterios.joinToString(" and ") }
    }

    override fun antesDePesquisar(params: Map<String, String>, query: TypedQuery<V>) {
        super.antesDePesquisar(params, query)

        if (params.containsKey("atualizado_apos")) {
            query.setParameter("atualizado_apos", ZonedDateTime.parse(params["atualizado_apos"]!!))
        }
    }

    override fun pesquisarOrderBy(params: Map<String, String>) = "atualizado_em asc"

    override fun excluir(entidade: V) {
        entidade.excluidoEm = ZonedDateTime.now()
    }
}