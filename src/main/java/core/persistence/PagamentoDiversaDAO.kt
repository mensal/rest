package core.persistence

import core.entity.PagamentoDiversa

open class PagamentoDiversaDAO protected constructor() : CrudDAO<PagamentoDiversa>() {

    override val entityClass = PagamentoDiversa::class.java
}