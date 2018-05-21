package core.persistence

import core.entity.PagamentoCombustivel
import javax.enterprise.inject.spi.CDI

open class PagamentoCombustivelDAO protected constructor() : CrudDAO<PagamentoCombustivel>() {

    override fun pesquisarWhere(ano: Int, mes: Int) = "year(data) = $ano and month(data) = $mes"

    override fun pesquisarOrderBy(ano: Int, mes: Int) = "data asc"

    companion object {
        fun instance() = CDI.current().select(PagamentoCombustivelDAO::class.java).get()!!
    }
}