package core.persistence

import core.entity.TipoDespesaDiversa

open class TipoDespesaDiversaDAO protected constructor() : VersionadoCrudDAO<TipoDespesaDiversa>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "nome asc"

}