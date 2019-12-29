package core.persistence

import core.entity.TipoDespesaDiversa
import javax.enterprise.context.Dependent

@Dependent
open class TipoDespesaDiversaDAO protected constructor() : VersionadoCrudDAO<TipoDespesaDiversa>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "nome asc"

}