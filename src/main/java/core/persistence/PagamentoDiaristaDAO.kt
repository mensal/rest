package core.persistence

import core.entity.PagamentoDiarista
import core.entity.TipoDespesaDiarista
import javax.enterprise.inject.spi.CDI

open class PagamentoDiaristaDAO protected constructor() : CrudDAO<PagamentoDiarista>() {

    override val entityClass = PagamentoDiarista::class.java

    open fun buscar(tipo: TipoDespesaDiarista): List<PagamentoDiarista> {
        val jpql = " select e from ${entityClass.name} e where e.tipo = :tipo order by e.data desc "
        val query = em.createQuery(jpql, PagamentoDiarista::class.java)
        query.setParameter("tipo", tipo)

        return query.resultList
    }

    companion object {
        fun instance() = CDI.current().select(PagamentoDiaristaDAO::class.java).get()!!
    }
}