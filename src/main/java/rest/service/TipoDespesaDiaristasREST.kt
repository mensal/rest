package rest.service

import core.entity.TipoDespesaDiarista
import core.persistence.TipoDespesaDiaristaDAO
import rest.data.TipoDespesaDiaristaReqData
import rest.data.TipoDespesaDiaristaResData
import javax.ws.rs.Path

@Path("tipo/diaristas")
open class TipoDespesaDiaristasREST : CrudREST<TipoDespesaDiarista, TipoDespesaDiaristaReqData, TipoDespesaDiaristaResData, TipoDespesaDiaristaDAO>() {

    override fun antesDeExcluir(entidade: TipoDespesaDiarista) {
//        if (!PagamentoDiaristaDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
    }
}
