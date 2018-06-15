package core.persistence

import core.entity.TipoDespesaDiversa
import javax.ws.rs.core.MultivaluedMap

open class TipoDespesaDiversaDAO protected constructor() : CrudDAO<TipoDespesaDiversa>() {

    override fun pesquisarOrderBy(params: MultivaluedMap<String, String>) = "nome asc"

}