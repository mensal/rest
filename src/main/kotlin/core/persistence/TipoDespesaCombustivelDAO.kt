package core.persistence

import core.entity.TipoDespesaCombustivel
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
    open lateinit var em2: EntityManager

    override fun pesquisar(params: Map<String, String>) = VersionadoCrudDAO.pesquisar2(params, TipoDespesaCombustivel::class, em2)

    override fun obter(id: UUID) = VersionadoCrudDAO.obter2(id, TipoDespesaCombustivel::class, em2)

    override fun inserir(entidade: TipoDespesaCombustivel) = VersionadoCrudDAO.inserir2(entidade, em2)

    override fun atualizar(entidade: TipoDespesaCombustivel) = VersionadoCrudDAO.atualizar2(entidade, em2)

    override fun excluir(entidade: TipoDespesaCombustivel) = VersionadoCrudDAO.excluir2(entidade, em2)
}