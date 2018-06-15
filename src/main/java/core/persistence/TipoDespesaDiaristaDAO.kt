package core.persistence

import core.entity.TipoDespesaDiarista
import javax.ws.rs.core.MultivaluedMap

open class TipoDespesaDiaristaDAO protected constructor() : CrudDAO<TipoDespesaDiarista>() {

    override fun pesquisarOrderBy(params: MultivaluedMap<String, String>) = "valor desc"

}