package core.persistence

import core.entity.PagamentoDiarista
import javax.enterprise.inject.spi.CDI

open class PagamentoDiaristaDAO protected constructor() : CrudDAO<PagamentoDiarista>() {

    override val entityClass = PagamentoDiarista::class.java

    override fun pesquisarWhere(ano: Int, mes: Int) = "year(data) = $ano and month(data) = $mes"

    override fun pesquisarOrderBy(ano: Int, mes: Int) = "data desc"

//    open fun buscar(tipo: TipoDespesaDiarista): List<PagamentoDiarista> {
//        val jpql = " select e from ${entityClass.name} e where e.tipo = :tipo order by e.data desc "
//        val query = em.createQuery(jpql, PagamentoDiarista::class.java)
//        query.setParameter("tipo", tipo)
//
//        return query.resultList
//    }

    companion object {
        fun instance() = CDI.current().select(PagamentoDiaristaDAO::class.java).get()!!
    }
}