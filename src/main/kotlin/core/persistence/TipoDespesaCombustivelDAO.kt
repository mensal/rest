package core.persistence

import core.entity.TipoDespesaCombustivel
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class TipoDespesaCombustivelDAO : VersionadoCrudDAO<TipoDespesaCombustivel>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "veiculo asc"
}