package core.persistence

import core.entity.TipoDespesaDiarista

open class TipoDespesaDiaristaDAO protected constructor() : CrudDAO<TipoDespesaDiarista>() {

    override fun pesquisarOrderBy(ano: Int, mes: Int) = "valor desc"

}