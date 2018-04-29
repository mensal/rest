package core.persistence

import core.entity.PagamentoFixa
import javax.enterprise.inject.spi.CDI

open class PagamentoFixaDAO protected constructor() : CrudDAO<PagamentoFixa>() {

//    override val entityClass = PagamentoFixa::class.java

    override fun pesquisarWhere(ano: Int, mes: Int) = "year(data) = $ano and month(data) = $mes"

    override fun pesquisarOrderBy(ano: Int, mes: Int) = "data desc"

//    open fun buscar(tipo: TipoDespesaFixa): List<PagamentoFixa> {
//        val jpql = " select e from ${entityClass.name} e where e.tipo = :tipo order by e.data desc "
//        val query = em.createQuery(jpql, PagamentoFixa::class.java)
//        query.setParameter("tipo", tipo)
//
//        return query.resultList
//    }

    companion object {
        fun instance() = CDI.current().select(PagamentoFixaDAO::class.java).get()!!
    }
}