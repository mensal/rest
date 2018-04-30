package core.persistence

import core.entity.PagamentoDiversa
import javax.enterprise.inject.spi.CDI

open class PagamentoDiversaDAO protected constructor() : CrudDAO<PagamentoDiversa>() {

//    override val entityClass = PagamentoDiversa::class.java

    override fun pesquisarWhere(ano: Int, mes: Int) = "year(data) = $ano and month(data) = $mes"

    override fun pesquisarOrderBy(ano: Int, mes: Int) = "data asc"

//    open fun buscar(tipo: TipoDespesaDiversa): List<PagamentoDiversa> {
//        val jpql = " select e from ${entityClass.name} e where e.tipo = :tipo order by e.data desc "
//        val query = em.createQuery(jpql, PagamentoDiversa::class.java)
//        query.setParameter("tipo", tipo)
//
//        return query.resultList
//    }

    companion object {
        fun instance() = CDI.current().select(PagamentoDiversaDAO::class.java).get()!!
    }
}