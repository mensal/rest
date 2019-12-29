package core.persistence

import core.entity.TipoDespesaDiarista
import javax.enterprise.context.Dependent

@Dependent
open class TipoDespesaDiaristaDAO protected constructor() : VersionadoCrudDAO<TipoDespesaDiarista>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "valor desc"

}