package core.persistence

import core.entity.PagamentoFixa
import javax.enterprise.inject.spi.CDI
import javax.ws.rs.core.MultivaluedMap

open class PagamentoFixaDAO protected constructor() : CrudDAO<PagamentoFixa>() {

    override fun pesquisarWhere(params: MultivaluedMap<String, String>) = "year(data) = ${params["ano"]?.first()} and month(data) = ${params["mes"]?.first()}"

    override fun pesquisarOrderBy(params: MultivaluedMap<String, String>) = "data asc"

    companion object {
        fun instance() = CDI.current().select(PagamentoFixaDAO::class.java).get()!!
    }
}