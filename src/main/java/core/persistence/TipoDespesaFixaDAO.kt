package core.persistence

import core.entity.TipoDespesaFixa

open class TipoDespesaFixaDAO protected constructor() : CrudDAO<TipoDespesaFixa>() {

    override val entityClass = TipoDespesaFixa::class.java

//    companion object {
//        fun instance() = CDI.current().select(TipoDespesaDiversaDAO::class.java).get()!!
//    }
}