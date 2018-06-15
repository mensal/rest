package core.persistence

import core.entity.PagamentoDiversa
import javax.enterprise.inject.spi.CDI
import javax.ws.rs.core.MultivaluedMap

open class PagamentoDiversaDAO protected constructor() : CrudDAO<PagamentoDiversa>() {

    override fun pesquisarWhere(params: MultivaluedMap<String, String>) = "year(data) = ${params["ano"]?.first()} and month(data) = ${params["mes"]?.first()}"

    override fun pesquisarOrderBy(params: MultivaluedMap<String, String>) = "data asc"

    companion object {
        fun instance() = CDI.current().select(PagamentoDiversaDAO::class.java).get()!!
    }
}