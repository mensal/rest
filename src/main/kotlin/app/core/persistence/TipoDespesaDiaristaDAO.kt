package app.core.persistence

import app.core.entity.TipoDespesaDiarista

class TipoDespesaDiaristaDAO protected constructor() : VersionadoCrudDAO<TipoDespesaDiarista>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "valor desc"

}