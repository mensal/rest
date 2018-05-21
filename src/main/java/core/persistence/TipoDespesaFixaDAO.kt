package core.persistence

import core.entity.TipoDespesaFixa

open class TipoDespesaFixaDAO protected constructor() : CrudDAO<TipoDespesaFixa>() {

    override fun pesquisarOrderBy(ano: Int, mes: Int) = "vencimento asc, nome asc"
}