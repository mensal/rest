package core.persistence

import core.entity.TipoDespesaDiversa

open class TipoDespesaDiversaDAO protected constructor() : CrudDAO<TipoDespesaDiversa>() {

    override val entityClass = TipoDespesaDiversa::class.java

//    companion object {
//        fun instance() = CDI.current().select(TipoDespesaDiversaDAO::class.java).get()!!
//    }
}