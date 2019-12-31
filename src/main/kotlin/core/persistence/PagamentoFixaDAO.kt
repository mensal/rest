package core.persistence

import core.entity.PagamentoFixa
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
open class PagamentoFixaDAO : PagamentoDAO<PagamentoFixa> {

    @Inject
    open lateinit var em: EntityManager

    override fun pesquisar(params: Map<String, String>) = pesquisar(params, PagamentoFixa::class, em)
    override fun obter(id: UUID) = obter(id, PagamentoFixa::class, em)
    override fun inserir(entidade: PagamentoFixa) = inserir(entidade, em)
    override fun atualizar(entidade: PagamentoFixa) = atualizar(entidade, em)
    override fun excluir(entidade: PagamentoFixa) = excluir(entidade, em)
}