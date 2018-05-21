package core.persistence

import core.entity.TipoDespesaCombustivel

open class TipoDespesaCombustivelDAO protected constructor() : CrudDAO<TipoDespesaCombustivel>() {

    override fun pesquisarOrderBy(ano: Int, mes: Int) = "veiculo asc"
}