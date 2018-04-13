package rest.service

import core.entity.TipoDespesaCombustivel
import core.persistence.PagamentoCombustivelDAO
import core.persistence.TipoDespesaCombustivelDAO
import rest.data.TipoDespesaCombustivelReqData
import rest.data.TipoDespesaCombustivelResData
import javax.inject.Inject
import javax.ws.rs.Path

@Path("tipo/combustiveis")
open class TipoDespesaCombustiveisREST : CrudREST<TipoDespesaCombustivel, TipoDespesaCombustivelReqData, TipoDespesaCombustivelResData, TipoDespesaCombustivelDAO>() {

    @Inject
    override lateinit var dao: TipoDespesaCombustivelDAO

    override fun novaEntidade() = TipoDespesaCombustivel()

    override fun novoRequestData() = TipoDespesaCombustivelReqData()

    override fun novoResponseData() = TipoDespesaCombustivelResData()

    override fun antesDeExcluir(entidade: TipoDespesaCombustivel) {
        if (!PagamentoCombustivelDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
    }
}