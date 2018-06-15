package core.persistence

import core.entity.PagamentoFixa
import javax.enterprise.inject.spi.CDI

open class PagamentoFixaDAO protected constructor() : CrudDAO<PagamentoFixa>() {

    override fun pesquisarWhere(params: Map<String, String>) = "year(data) = ${params["ano"]} and month(data) = ${params["mes"]}"

    override fun pesquisarOrderBy(params: Map<String, String>) = "data asc"

    companion object {
        fun instance() = CDI.current().select(PagamentoFixaDAO::class.java).get()!!
    }
}