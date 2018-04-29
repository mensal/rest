package core.persistence

import core.entity.PagamentoDiarista
import java.math.BigDecimal
import javax.enterprise.inject.spi.CDI

open class PagamentoDiaristaDAO protected constructor() : CrudDAO<PagamentoDiarista>() {

    override fun pesquisarWhere(ano: Int, mes: Int) = "year(data) = $ano and month(data) = $mes"

    override fun pesquisarOrderBy(ano: Int, mes: Int) = "data desc"

//    open fun buscar(tipo: TipoDespesaDiarista): List<PagamentoDiarista> {
//        val jpql = " select e from ${entityClass.name} e where e.tipo = :tipo order by e.data desc "
//        val query = em.createQuery(jpql, PagamentoDiarista::class.java)
//        query.setParameter("tipo", tipo)
//
//        return query.resultList
//    }

    open fun saldo(ano: Int, mes: Int): BigDecimal {
        var jpql = ""

        jpql += " select e from PagamentoDiarista e where e.tipo = :tipo order by e.data desc "

        val query = em.createQuery(jpql, BigDecimal::class.java)

        return query.singleResult
    }

    companion object {
        fun instance() = CDI.current().select(PagamentoDiaristaDAO::class.java).get()!!
    }
}