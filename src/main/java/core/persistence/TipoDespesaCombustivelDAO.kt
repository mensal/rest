package core.persistence

import core.entity.TipoDespesaCombustivel
import javax.ws.rs.core.MultivaluedMap

open class TipoDespesaCombustivelDAO protected constructor() : CrudDAO<TipoDespesaCombustivel>() {

    override fun pesquisarOrderBy(params: MultivaluedMap<String, String>) = "veiculo asc"
}