package core.persistence

import core.entity.TipoDespesaDiversa

open class TipoDespesaDiversaDAO protected constructor() : CrudDAO<TipoDespesaDiversa>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "nome asc"

}