package core.persistence

import core.entity.PagamentoDiversa
import core.entity.TipoDespesaDiversa
import javax.enterprise.inject.spi.CDI

open class PagamentoDiversaDAO protected constructor() : CrudDAO<PagamentoDiversa>() {

    override val entityClass = PagamentoDiversa::class.java

    open fun buscar(tipo: TipoDespesaDiversa): List<PagamentoDiversa> {
        val jpql = " select e from ${entityClass.name} e where e.tipo = :tipo order by e.data desc "
        val query = em.createQuery(jpql, PagamentoDiversa::class.java)
        query.setParameter("tipo", tipo)

        return query.resultList
    }

    companion object {
        fun instance() = CDI.current().select(PagamentoDiversaDAO::class.java).get()!!
    }
}