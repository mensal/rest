package rest.service

import core.entity.TipoDespesaCombustivel
import core.persistence.TipoDespesaCombustivelDAO
import rest.data.TipoDespesaCombustivelReqData
import rest.data.TipoDespesaCombustivelResData
import javax.ws.rs.Path

@Path("api/tipo/combustiveis")
class TipoDespesaCombustiveisREST : CrudREST<TipoDespesaCombustivel, TipoDespesaCombustivelReqData, TipoDespesaCombustivelResData, TipoDespesaCombustivelDAO>() {

    override fun antesDeExcluir(entidade: TipoDespesaCombustivel) {
//        if (!PagamentoCombustivelDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
    }
}