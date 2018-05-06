package core.persistence

import core.entity.PagamentoDiarista
import java.math.BigDecimal
import java.time.LocalDate
import java.time.YearMonth
import javax.enterprise.inject.spi.CDI

open class PagamentoDiaristaDAO protected constructor() : CrudDAO<PagamentoDiarista>() {

    override fun pesquisarWhere(ano: Int, mes: Int) = "year(data) = $ano and month(data) = $mes"

    override fun pesquisarOrderBy(ano: Int, mes: Int) = "data asc"

//    open fun buscar(tipo: TipoDespesaDiarista): List<PagamentoDiarista> {
//        val jpql = " select e from ${entityClass.name} e where e.tipo = :tipo order by e.data desc "
//        val query = em.createQuery(jpql, PagamentoDiarista::class.java)
//        query.setParameter("tipo", tipo)
//
//        return query.resultList
//    }

    open fun pagoAte(ano: Int, mes: Int): BigDecimal {
        var jpql = ""
        ultimoDia(ano, mes)

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
        ultimoDia(ano, mes)

        jpql += " select sum(t.valor) "
        jpql += "   from PagamentoDiarista pd "
        jpql += "   join pd.tipo t "
        jpql += "  where pd.data < :data "

        val query = em.createQuery(jpql, BigDecimal::class.java)
        query.setParameter("data", ultimoDia(ano, mes))

        return query.singleResult
    }

    internal open fun ultimoDia(ano: Int, mes: Int) = LocalDate.of(ano, mes, YearMonth.of(ano, mes).lengthOfMonth())

    companion object {
        fun instance() = CDI.current().select(PagamentoDiaristaDAO::class.java).get()!!
    }
}