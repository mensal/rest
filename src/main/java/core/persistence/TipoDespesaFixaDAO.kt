package core.persistence

import core.entity.TipoDespesaFixa

open class TipoDespesaFixaDAO protected constructor() : CrudDAO<TipoDespesaFixa>() {

//    override val entityClass = TipoDespesaFixa::class.java

    override fun pesquisarOrderBy(ano: Int, mes: Int) = "vencimento desc, nome asc"

//    companion object {
//        fun instance() = CDI.current().select(TipoDespesaDiversaDAO::class.java).get()!!
//    }
}