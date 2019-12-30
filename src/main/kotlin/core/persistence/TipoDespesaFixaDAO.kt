package core.persistence

import core.entity.TipoDespesaFixa
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class TipoDespesaFixaDAO : VersionadoCrudDAO<TipoDespesaFixa>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "vencimento asc, nome asc"
}