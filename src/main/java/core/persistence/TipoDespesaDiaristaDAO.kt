package core.persistence

import core.entity.TipoDespesaDiarista

open class TipoDespesaDiaristaDAO protected constructor() : VersionadoCrudDAO<TipoDespesaDiarista>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "valor desc"

}