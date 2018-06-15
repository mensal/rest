package core.persistence

import core.entity.PagamentoCombustivel
import javax.enterprise.inject.spi.CDI

open class PagamentoCombustivelDAO protected constructor() : CrudDAO<PagamentoCombustivel>() {

    override fun pesquisarWhere(params: Map<String, String>) = "year(data) = ${params["ano"]} and month(data) = ${params["mes"]}"

    override fun pesquisarOrderBy(params: Map<String, String>) = "data asc"

    companion object {
        fun instance() = CDI.current().select(PagamentoCombustivelDAO::class.java).get()!!
    }
}