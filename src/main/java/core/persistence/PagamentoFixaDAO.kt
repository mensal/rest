package core.persistence

import core.entity.PagamentoFixa
import javax.enterprise.inject.spi.CDI

open class PagamentoFixaDAO protected constructor() : CrudDAO<PagamentoFixa>() {

    override fun pesquisarWhere(ano: Int, mes: Int) = "year(data) = $ano and month(data) = $mes"

    override fun pesquisarOrderBy(ano: Int, mes: Int) = "data asc"

    companion object {
        fun instance() = CDI.current().select(PagamentoFixaDAO::class.java).get()!!
    }
}