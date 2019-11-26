package app.core.persistence

import app.core.entity.TipoDespesaFixa
import org.springframework.stereotype.Controller

@Controller
class TipoDespesaFixaDAO protected constructor() : VersionadoCrudDAO<TipoDespesaFixa>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "vencimento asc, nome asc"
}