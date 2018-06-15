package core.persistence

import core.entity.PagamentoDiversa
import javax.enterprise.inject.spi.CDI

open class PagamentoDiversaDAO protected constructor() : CrudDAO<PagamentoDiversa>() {

    override fun pesquisarWhere(params: Map<String, String>) = "year(data) = ${params["ano"]} and month(data) = ${params["mes"]}"

    override fun pesquisarOrderBy(params: Map<String, String>) = "data asc"

    companion object {
        fun instance() = CDI.current().select(PagamentoDiversaDAO::class.java).get()!!
    }
}