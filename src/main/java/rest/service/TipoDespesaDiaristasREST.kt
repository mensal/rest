package rest.service

import core.entity.TipoDespesaDiarista
import core.persistence.TipoDespesaDiaristaDAO
import rest.data.TipoDespesaDiaristaReqData
import rest.data.TipoDespesaDiaristaResData
import javax.ws.rs.Path

@Path("tipo/diaristas")
open class TipoDespesaDiaristasREST : CrudREST<TipoDespesaDiarista, TipoDespesaDiaristaReqData, TipoDespesaDiaristaResData, TipoDespesaDiaristaDAO>() {

//    @Inject
//    override lateinit var dao: TipoDespesaDiaristaDAO

//    override fun novaEntidade() = TipoDespesaDiarista()

//    override fun novoRequestData() = TipoDespesaDiaristaReqData()

//    override fun novoResponseData() = TipoDespesaDiaristaResData()

    override fun antesDeExcluir(entidade: TipoDespesaDiarista) {
//        if (!PagamentoDiaristaDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
    }
}
