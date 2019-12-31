package core.persistence

import core.entity.Pagamento.Companion.ultimoDia
import core.entity.PagamentoDiarista
import java.math.BigDecimal
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class PagamentoDiaristaDAO : PagamentoDAO<PagamentoDiarista> {

    @Inject
    open lateinit var em2: EntityManager

    override fun pesquisar(params: Map<String, String>) = PagamentoDAO.pesquisar(params, PagamentoDiarista::class, em2)

    override fun obter(id: UUID) = PagamentoDAO.obter(id, PagamentoDiarista::class, em2)

    override fun inserir(entidade: PagamentoDiarista) = PagamentoDAO.inserir(entidade, em2)

    override fun atualizar(entidade: PagamentoDiarista) = PagamentoDAO.atualizar(entidade, em2)

    override fun excluir(entidade: PagamentoDiarista) = PagamentoDAO.excluir(entidade, em2)

    open fun pagoAte(ano: Int, mes: Int): BigDecimal {
        var jpql = ""

        jpql += " select sum(up.valor) "
        jpql += "   from UsuarioPagamento up "
        jpql += "   join up.pagamento p, "
        jpql += "        PagamentoDiarista pd "
        jpql += "  where p.id = pd.id "
        jpql += "    and pd.data < :data "
        jpql += "    and pd.excluidoEm is null "

        val query = em2.createQuery(jpql, BigDecimal::class.java)
        query.setParameter("data", ultimoDia(ano, mes))

        return query.singleResult ?: BigDecimal.ZERO
    }

    open fun devidoAte(ano: Int, mes: Int): BigDecimal {
        var jpql = ""

        jpql += " select sum(t.valor) "
        jpql += "   from PagamentoDiarista pd "
        jpql += "   join pd.tipo t "
        jpql += "  where pd.data < :data "
        jpql += "    and pd.excluidoEm is null "

        val query = em2.createQuery(jpql, BigDecimal::class.java)
        query.setParameter("data", ultimoDia(ano, mes))

        return query.singleResult ?: BigDecimal.ZERO
    }
}