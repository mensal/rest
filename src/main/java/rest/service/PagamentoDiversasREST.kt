package rest.service

import core.entity.PagamentoDiversa
import core.entity.TipoDespesaDiversa
import core.persistence.PagamentoDiversaDAO
import core.persistence.TipoDespesaDiversaDAO
import rest.data.PagamentoDiversaReqData
import rest.data.PagamentoDiversaResData
import javax.ws.rs.Path

@Path("pagamento/diversas")
open class PagamentoDiversasREST : PagamentoREST<PagamentoDiversa, TipoDespesaDiversa, PagamentoDiversaReqData, PagamentoDiversaResData, PagamentoDiversaDAO, TipoDespesaDiversaDAO>() {

//    @Inject
//    override lateinit var dao: PagamentoDiversaDAO

//    @Inject
//    override lateinit var tipoDAO: TipoDespesaDiversaDAO

//    override fun novaEntidade() = PagamentoDiversa()

//    override fun novoRequestData() = PagamentoDiversaReqData()

//    override fun novoResponseData() = PagamentoDiversaResData()
}