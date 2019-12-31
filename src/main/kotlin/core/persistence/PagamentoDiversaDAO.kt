package core.persistence

import core.entity.PagamentoDiversa
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class PagamentoDiversaDAO : PagamentoDAO<PagamentoDiversa> {

    @Inject
    open lateinit var em2: EntityManager

    override fun pesquisar(params: Map<String, String>) = PagamentoDAO.pesquisar(params, PagamentoDiversa::class, em2)

    override fun obter(id: UUID) = PagamentoDAO.obter(id, PagamentoDiversa::class, em2)

    override fun inserir(entidade: PagamentoDiversa) = PagamentoDAO.inserir(entidade, em2)

    override fun atualizar(entidade: PagamentoDiversa) = PagamentoDAO.atualizar(entidade, em2)

    override fun excluir(entidade: PagamentoDiversa) = PagamentoDAO.excluir(entidade, em2)
}