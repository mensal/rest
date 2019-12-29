package core.persistence

import core.entity.Pagamento.Companion.ultimoDia
import core.entity.PagamentoDiarista
import java.math.BigDecimal
import javax.enterprise.context.Dependent
import javax.enterprise.inject.spi.CDI

@Dependent
open class PagamentoDiaristaDAO protected constructor() : PagamentoDAO<PagamentoDiarista>() {

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

    companion object {
        fun instance() = CDI.current().select(PagamentoDiaristaDAO::class.java).get()!!
    }
}