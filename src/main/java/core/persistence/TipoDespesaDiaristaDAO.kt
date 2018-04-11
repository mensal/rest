package core.persistence

import core.entity.TipoDespesaDiarista

open class TipoDespesaDiaristaDAO protected constructor() : CrudDAO<TipoDespesaDiarista>() {

    override val entityClass = TipoDespesaDiarista::class.java

//    companion object {
//        fun instance() = CDI.current().select(TipoDespesaDiaristaDAO::class.java).get()!!
//    }
}