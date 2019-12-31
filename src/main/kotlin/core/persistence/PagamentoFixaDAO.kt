package core.persistence

import core.entity.PagamentoFixa
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class PagamentoFixaDAO : PagamentoDAO<PagamentoFixa> {

    @Inject
    open lateinit var em2: EntityManager

    override fun pesquisar(params: Map<String, String>) = PagamentoDAO.pesquisar(params, PagamentoFixa::class, em2)

    override fun obter(id: UUID) = PagamentoDAO.obter(id, PagamentoFixa::class, em2)

    override fun inserir(entidade: PagamentoFixa) = PagamentoDAO.inserir(entidade, em2)

    override fun atualizar(entidade: PagamentoFixa) = PagamentoDAO.atualizar(entidade, em2)

    override fun excluir(entidade: PagamentoFixa) = PagamentoDAO.excluir(entidade, em2)
}