package core.persistence

import core.entity.Versionado
import java.time.ZonedDateTime
import javax.persistence.TypedQuery
import javax.transaction.Transactional

@Transactional
abstract class VersionadoCrudDAO<V : Versionado> : CrudDAO<V>() {

    override fun pesquisarWhere(params: Map<String, String>): String? {
        var criterios = mutableListOf<String?>()
        super.pesquisarWhere(params)?.let { criterios.add(it) }

        if (params.containsKey("atualizado_apos")) {
            criterios.add("atualizado_em > :atualizado_apos")
        }

        return criterios.joinToString(" and ")
    }

    override fun antesDePesquisar(params: Map<String, String>, query: TypedQuery<V>) {
        super.antesDePesquisar(params, query)

        if (params.containsKey("atualizado_apos")) {
            query.setParameter("atualizado_apos", ZonedDateTime.parse(params["atualizado_apos"]!!))
        }
    }

    override fun pesquisarOrderBy(params: Map<String, String>) = "atualizado_em asc"
}