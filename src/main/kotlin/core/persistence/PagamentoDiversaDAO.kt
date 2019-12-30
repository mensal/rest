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

    override fun pesquisar(params: Map<String, String>) = PagamentoDAO.pesquisar2(params, PagamentoDiversa::class, em2)

    override fun obter(id: UUID) = PagamentoDAO.obter2(id, PagamentoDiversa::class, em2)

    override fun inserir(entidade: PagamentoDiversa) = PagamentoDAO.inserir2(entidade, em2)

    override fun atualizar(entidade: PagamentoDiversa) = PagamentoDAO.atualizar2(entidade, em2)

    override fun excluir(entidade: PagamentoDiversa) = PagamentoDAO.excluir2(entidade, em2)
}