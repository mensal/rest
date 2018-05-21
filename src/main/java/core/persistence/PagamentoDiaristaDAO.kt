package core.persistence

import core.entity.Pagamento.Companion.ultimoDia
import core.entity.PagamentoDiarista
import java.math.BigDecimal
import javax.enterprise.inject.spi.CDI

open class PagamentoDiaristaDAO protected constructor() : CrudDAO<PagamentoDiarista>() {

    override fun pesquisarWhere(ano: Int, mes: Int) = "year(data) = $ano and month(data) = $mes"

    override fun pesquisarOrderBy(ano: Int, mes: Int) = "data asc"

    open fun pagoAte(ano: Int, mes: Int): BigDecimal {
        var jpql = ""

        jpql += " select sum(up.valor) "
        jpql += "   from UsuarioPagamento up "
        jpql += "   join up.pagamento p, "
        jpql += "        PagamentoDiarista pd "
        jpql += "  where p.id = pd.id "
        jpql += "    and pd.data < :data "

        val query = em.createQuery(jpql, BigDecimal::class.java)
        query.setParameter("data", ultimoDia(ano, mes))

        return query.singleResult
    }

    open fun devidoAte(ano: Int, mes: Int): BigDecimal {
        var jpql = ""

        jpql += " select sum(t.valor) "
        jpql += "   from PagamentoDiarista pd "
        jpql += "   join pd.tipo t "
        jpql += "  where pd.data < :data "

        val query = em.createQuery(jpql, BigDecimal::class.java)
        query.setParameter("data", ultimoDia(ano, mes))

        return query.singleResult
    }

    companion object {
        fun instance() = CDI.current().select(PagamentoDiaristaDAO::class.java).get()!!
    }
}