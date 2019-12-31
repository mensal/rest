package core.persistence

import core.entity.PagamentoCombustivel
import core.persistence.PagamentoDAO.Companion.atualizar
import core.persistence.PagamentoDAO.Companion.excluir
import core.persistence.PagamentoDAO.Companion.inserir
import core.persistence.PagamentoDAO.Companion.obter
import core.persistence.PagamentoDAO.Companion.pesquisar
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class PagamentoCombustivelDAO : PagamentoDAO<PagamentoCombustivel> {

    @Inject
    open lateinit var em: EntityManager

    override fun pesquisar(params: Map<String, String>) = pesquisar(params, PagamentoCombustivel::class, em)
    override fun obter(id: UUID) = obter(id, PagamentoCombustivel::class, em)
    override fun inserir(entidade: PagamentoCombustivel) = inserir(entidade, em)
    override fun atualizar(entidade: PagamentoCombustivel) = atualizar(entidade, em)
    override fun excluir(entidade: PagamentoCombustivel) = excluir(entidade, em)
}