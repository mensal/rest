package rest.service

import core.entity.TipoDespesaFixa
import core.persistence.TipoDespesaFixaDAO
import rest.data.TipoDespesaFixaReqData
import rest.data.TipoDespesaFixaResData
import javax.ws.rs.Path

@Path("api/tipo/fixas")
class TipoDespesaFixasREST : CrudREST<TipoDespesaFixa, TipoDespesaFixaReqData, TipoDespesaFixaResData, TipoDespesaFixaDAO>() {

    override fun antesDeExcluir(entidade: TipoDespesaFixa) {
//        if (!PagamentoFixaDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
    }
}