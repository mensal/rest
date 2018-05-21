package core.persistence

import core.entity.PagamentoDiversa
import javax.enterprise.inject.spi.CDI

open class PagamentoDiversaDAO protected constructor() : CrudDAO<PagamentoDiversa>() {

    override fun pesquisarWhere(ano: Int, mes: Int) = "year(data) = $ano and month(data) = $mes"

    override fun pesquisarOrderBy(ano: Int, mes: Int) = "data asc"

    companion object {
        fun instance() = CDI.current().select(PagamentoDiversaDAO::class.java).get()!!
    }
}