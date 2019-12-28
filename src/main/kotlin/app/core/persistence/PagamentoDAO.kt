package app.core.persistence

import app.core.entity.Pagamento
import javax.persistence.TypedQuery

class PagamentoDAO<E : Pagamento<*>> protected constructor() : VersionadoCrudDAO<E>() {

    override fun pesquisarWhere(params: Map<String, String>): String {
        var criterios = mutableListOf<String>()
        super.pesquisarWhere(params).let { if (it.isNotBlank()) criterios.add(it) }

        if (params.containsKey("ano")) {
            criterios.add("year(data) = :ano")
        }

        if (params.containsKey("mes")) {
            criterios.add("month(data) = :mes")
        }

        return criterios.let { if (it.isEmpty()) "" else criterios.joinToString(" and ") }
    }

    override fun antesDePesquisar(params: Map<String, String>, query: TypedQuery<E>) {
        super.antesDePesquisar(params, query)

        if (params.containsKey("ano")) {
            query.setParameter("ano", params["ano"]?.toIntOrNull())
        }

        if (params.containsKey("mes")) {
            query.setParameter("mes", params["mes"]?.toIntOrNull())
        }
    }

    override fun pesquisarOrderBy(params: Map<String, String>) = "data asc, atualizadoEm asc"
}