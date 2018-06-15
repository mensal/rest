package core.persistence

import core.entity.TipoDespesaFixa
import javax.ws.rs.core.MultivaluedMap

open class TipoDespesaFixaDAO protected constructor() : CrudDAO<TipoDespesaFixa>() {

    override fun pesquisarOrderBy(params: MultivaluedMap<String, String>) = "vencimento asc, nome asc"
}