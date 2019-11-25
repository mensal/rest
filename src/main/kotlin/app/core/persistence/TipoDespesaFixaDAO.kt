package app.core.persistence

import app.core.entity.TipoDespesaFixa

open class TipoDespesaFixaDAO protected constructor() : VersionadoCrudDAO<TipoDespesaFixa>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "vencimento asc, nome asc"
}