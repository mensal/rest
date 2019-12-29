package rest.service

import core.entity.TipoDespesaDiversa
import core.persistence.TipoDespesaDiversaDAO
import rest.data.TipoDespesaDiversaReqData
import rest.data.TipoDespesaDiversaResData
import javax.ws.rs.Path

@Path("tipo/diversas")
class TipoDespesaDiversasREST : CrudREST<TipoDespesaDiversa, TipoDespesaDiversaReqData, TipoDespesaDiversaResData, TipoDespesaDiversaDAO>() {

    override fun antesDeExcluir(entidade: TipoDespesaDiversa) {
//        if (!PagamentoDiversaDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
    }
}