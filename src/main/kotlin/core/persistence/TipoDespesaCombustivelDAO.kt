package core.persistence

import core.entity.TipoDespesaCombustivel
import javax.enterprise.context.Dependent

@Dependent
open class TipoDespesaCombustivelDAO protected constructor() : VersionadoCrudDAO<TipoDespesaCombustivel>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "veiculo asc"
}