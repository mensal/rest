package core.persistence

import core.entity.TipoDespesaDiversa

open class TipoDespesaDiversaDAO protected constructor() : CrudDAO<TipoDespesaDiversa>() {

    override fun pesquisarOrderBy(ano: Int, mes: Int) = "nome asc"

}