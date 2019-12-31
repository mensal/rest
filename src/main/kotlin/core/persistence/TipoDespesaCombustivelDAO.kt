package core.persistence

import core.entity.TipoDespesaCombustivel
import core.persistence.VersionadoCrudDAO.Companion.atualizar
import core.persistence.VersionadoCrudDAO.Companion.excluir
import core.persistence.VersionadoCrudDAO.Companion.inserir
import core.persistence.VersionadoCrudDAO.Companion.obter
import core.persistence.VersionadoCrudDAO.Companion.pesquisar
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class TipoDespesaCombustivelDAO : VersionadoCrudDAO<TipoDespesaCombustivel> {

//    override fun pesquisarOrderBy(params: Map<String, String>) = "veiculo asc"

    @Inject
    open lateinit var em: EntityManager

    override fun pesquisar(params: Map<String, String>) = pesquisar(params, TipoDespesaCombustivel::class, em)
    override fun obter(id: UUID) = obter(id, TipoDespesaCombustivel::class, em)
    override fun inserir(entidade: TipoDespesaCombustivel) = inserir(entidade, em)
    override fun atualizar(entidade: TipoDespesaCombustivel) = atualizar(entidade, em)
    override fun excluir(entidade: TipoDespesaCombustivel) = excluir(entidade, em)
}