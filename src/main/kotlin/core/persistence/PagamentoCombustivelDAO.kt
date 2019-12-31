package core.persistence

import core.entity.PagamentoCombustivel
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class PagamentoCombustivelDAO : PagamentoDAO<PagamentoCombustivel> {

    @Inject
    open lateinit var em2: EntityManager

    override fun pesquisar(params: Map<String, String>) = PagamentoDAO.pesquisar(params, PagamentoCombustivel::class, em2)

    override fun obter(id: UUID) = PagamentoDAO.obter(id, PagamentoCombustivel::class, em2)

    override fun inserir(entidade: PagamentoCombustivel) = PagamentoDAO.inserir(entidade, em2)

    override fun atualizar(entidade: PagamentoCombustivel) = PagamentoDAO.atualizar(entidade, em2)

    override fun excluir(entidade: PagamentoCombustivel) = PagamentoDAO.excluir(entidade, em2)
}