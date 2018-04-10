package core.persistence

import core.entity.PagamentoDiversa
import core.entity.TipoDespesaDiversa
import javax.enterprise.inject.spi.CDI

open class PagamentoDiversaDAO protected constructor() : CrudDAO<PagamentoDiversa>() {

    override val entityClass = PagamentoDiversa::class.java

    open fun buscar(tipo: TipoDespesaDiversa): List<PagamentoDiversa> {
        val jpql = " select pd from PagamentoDiversa pd where pd.tipo = :tipo order by pd.tipo.nome "
        val query = em.createQuery(jpql, PagamentoDiversa::class.java)
        query.setParameter("tipo", tipo)

        return query.resultList
    }

    companion object {
        fun instance() = CDI.current().select(PagamentoDiversaDAO::class.java).get()!!
    }
}