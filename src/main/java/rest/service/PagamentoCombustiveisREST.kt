package rest.service

import core.entity.PagamentoCombustivel
import core.entity.TipoDespesaCombustivel
import core.persistence.PagamentoCombustivelDAO
import core.persistence.TipoDespesaCombustivelDAO
import rest.data.PagamentoCombustivelReqData
import rest.data.PagamentoCombustivelResData
import javax.ws.rs.Path

@Path("pagamento/combustiveis")
open class PagamentoCombustiveisREST : PagamentoREST<PagamentoCombustivel, TipoDespesaCombustivel, PagamentoCombustivelReqData, PagamentoCombustivelResData, PagamentoCombustivelDAO, TipoDespesaCombustivelDAO>() {

//    @Inject
//    protected override lateinit var dao: PagamentoCombustivelDAO

//    @Inject
//    override lateinit var tipoDAO: TipoDespesaCombustivelDAO

//    override fun novaEntidade() = PagamentoCombustivel()

//    override fun novoRequestData() = PagamentoCombustivelReqData()

//    override fun novoResponseData() = PagamentoCombustivelResData()
}