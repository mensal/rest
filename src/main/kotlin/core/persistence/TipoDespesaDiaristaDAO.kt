package core.persistence

import core.entity.TipoDespesaDiarista
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class TipoDespesaDiaristaDAO : VersionadoCrudDAO<TipoDespesaDiarista>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "valor desc"
}