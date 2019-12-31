package core.persistence

import core.entity.TipoDespesaDiversa
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class TipoDespesaDiversaDAO : VersionadoCrudDAO<TipoDespesaDiversa> {

//    override fun pesquisarOrderBy(params: Map<String, String>) = "nome asc"

    @Inject
    open lateinit var em2: EntityManager

    override fun pesquisar(params: Map<String, String>) = VersionadoCrudDAO.pesquisar(params, TipoDespesaDiversa::class, em2)

    override fun obter(id: UUID) = VersionadoCrudDAO.obter(id, TipoDespesaDiversa::class, em2)

    override fun inserir(entidade: TipoDespesaDiversa) = VersionadoCrudDAO.inserir(entidade, em2)

    override fun atualizar(entidade: TipoDespesaDiversa) = VersionadoCrudDAO.atualizar(entidade, em2)

    override fun excluir(entidade: TipoDespesaDiversa) = VersionadoCrudDAO.excluir(entidade, em2)
}