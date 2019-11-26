package app.core.persistence

import app.core.entity.TipoDespesaDiversa
import org.springframework.stereotype.Controller

@Controller
class TipoDespesaDiversaDAO protected constructor() : VersionadoCrudDAO<TipoDespesaDiversa>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "nome asc"

}