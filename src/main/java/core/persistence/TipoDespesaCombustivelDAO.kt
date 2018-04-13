package core.persistence

import core.entity.TipoDespesaCombustivel

open class TipoDespesaCombustivelDAO protected constructor() : CrudDAO<TipoDespesaCombustivel>() {

    override val entityClass = TipoDespesaCombustivel::class.java

//    companion object {
//        fun instance() = CDI.current().select(TipoDespesaDiversaDAO::class.java).get()!!
//    }
}