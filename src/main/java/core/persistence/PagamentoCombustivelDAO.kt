package core.persistence

import core.entity.PagamentoCombustivel
import javax.enterprise.inject.spi.CDI
import javax.ws.rs.core.MultivaluedMap

open class PagamentoCombustivelDAO protected constructor() : CrudDAO<PagamentoCombustivel>() {

    override fun pesquisarWhere(params: MultivaluedMap<String, String>) = "year(data) = ${params["ano"]?.first()} and month(data) = ${params["mes"]?.first()}"

    override fun pesquisarOrderBy(params: MultivaluedMap<String, String>) = "data asc"

    companion object {
        fun instance() = CDI.current().select(PagamentoCombustivelDAO::class.java).get()!!
    }
}