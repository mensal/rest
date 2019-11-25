package app.core.persistence

import app.core.entity.TipoDespesaCombustivel

class TipoDespesaCombustivelDAO protected constructor() : VersionadoCrudDAO<TipoDespesaCombustivel>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "veiculo asc"
}