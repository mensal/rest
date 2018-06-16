package core.persistence

import core.entity.Pagamento
import java.time.ZonedDateTime
import javax.persistence.TypedQuery

open class PagamentoDAO<E : Pagamento<*>> protected constructor() : CrudDAO<E>() {

    override fun pesquisarWhere(params: Map<String, String>): String {
        var criterios = mutableListOf<String>()

        if (params.containsKey("ano")) {
            criterios.add("year(data) = :ano")
        }

        if (params.containsKey("mes")) {
            criterios.add("month(data) = :mes")
        }

        if (params.containsKey("atualizado_apos")) {
            criterios.add("atualizado_em > :atualizado_apos")
        }

        return criterios.joinToString(" and ")
    }

    override fun antesDePesquisar(params: Map<String, String>, query: TypedQuery<E>) {
        if (params.containsKey("ano")) {
            query.setParameter("ano", params["ano"]!!.toInt())
        }

        if (params.containsKey("mes")) {
            query.setParameter("mes", params["mes"]!!.toInt())
        }

        if (params.containsKey("atualizado_apos")) {
            query.setParameter("atualizado_apos", ZonedDateTime.parse(params["atualizado_apos"]!!))
        }
    }

    override fun pesquisarOrderBy(params: Map<String, String>) = "data asc"
}