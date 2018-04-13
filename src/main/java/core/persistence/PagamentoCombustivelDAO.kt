package core.persistence

import core.entity.PagamentoCombustivel
import core.entity.TipoDespesaCombustivel
import javax.enterprise.inject.spi.CDI

open class PagamentoCombustivelDAO protected constructor() : CrudDAO<PagamentoCombustivel>() {

    override val entityClass = PagamentoCombustivel::class.java

    open fun buscar(tipo: TipoDespesaCombustivel): List<PagamentoCombustivel> {
        val jpql = " select e from ${entityClass.name} e where e.tipo = :tipo order by e.data desc "
        val query = em.createQuery(jpql, PagamentoCombustivel::class.java)
        query.setParameter("tipo", tipo)

        return query.resultList
    }

    companion object {
        fun instance() = CDI.current().select(PagamentoCombustivelDAO::class.java).get()!!
    }
}