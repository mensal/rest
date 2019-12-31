package core.persistence

import core.entity.Pagamento.Companion.ultimoDia
import core.entity.PagamentoDiarista
import core.persistence.PagamentoDAO.Companion.atualizar
import core.persistence.PagamentoDAO.Companion.excluir
import core.persistence.PagamentoDAO.Companion.inserir
import core.persistence.PagamentoDAO.Companion.obter
import core.persistence.PagamentoDAO.Companion.pesquisar
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
    open lateinit var em: EntityManager

    open fun pagoAte(ano: Int, mes: Int): BigDecimal {
        var jpql = ""

        jpql += " select sum(up.valor) "
        jpql += "   from UsuarioPagamento up "
        jpql += "   join up.pagamento p, "
        jpql += "        PagamentoDiarista pd "
        jpql += "  where p.id = pd.id "
        jpql += "    and pd.data < :data "
        jpql += "    and pd.excluidoEm is null "

        val query = em.createQuery(jpql, BigDecimal::class.java)
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

        val query = em.createQuery(jpql, BigDecimal::class.java)
        query.setParameter("data", ultimoDia(ano, mes))

        return query.singleResult ?: BigDecimal.ZERO
    }

    override fun pesquisar(params: Map<String, String>) = pesquisar(params, PagamentoDiarista::class, em)
    override fun obter(id: UUID) = obter(id, PagamentoDiarista::class, em)
    override fun inserir(entidade: PagamentoDiarista) = inserir(entidade, em)
    override fun atualizar(entidade: PagamentoDiarista) = atualizar(entidade, em)
    override fun excluir(entidade: PagamentoDiarista) = excluir(entidade, em)
}