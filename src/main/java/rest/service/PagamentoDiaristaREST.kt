package rest.service

import core.entity.PagamentoDiarista
import core.entity.TipoDespesaDiarista
import core.persistence.PagamentoDiaristaDAO
import core.persistence.TipoDespesaDiaristaDAO
import rest.data.PagamentoDiaristaReqData
import rest.data.PagamentoDiaristaResData
import javax.ws.rs.Path

@Path("pagamento/diaristas")
open class PagamentoDiaristaREST : PagamentoREST<PagamentoDiarista, TipoDespesaDiarista, PagamentoDiaristaReqData, PagamentoDiaristaResData, PagamentoDiaristaDAO, TipoDespesaDiaristaDAO>() {

//    @Inject
//    override lateinit var dao: PagamentoDiaristaDAO

//    @Inject
//    override lateinit var tipoDAO: TipoDespesaDiaristaDAO

//    override fun novaEntidade() = PagamentoDiarista()

//    override fun novoRequestData() = PagamentoDiaristaReqData()

//    override fun novoResponseData() = PagamentoDiaristaResData()
}