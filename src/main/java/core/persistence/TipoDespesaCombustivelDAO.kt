package core.persistence

import core.entity.TipoDespesaCombustivel

open class TipoDespesaCombustivelDAO protected constructor() : VersionadoCrudDAO<TipoDespesaCombustivel>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "veiculo asc"
}