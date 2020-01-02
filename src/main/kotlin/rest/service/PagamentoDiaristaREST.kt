//package rest.service
//
//import core.entity.PagamentoDiarista
//import core.entity.TipoDespesaDiarista
//import core.persistence.PagamentoDiaristaDAO
//import core.persistence.TipoDespesaDiaristaDAO
//import rest.data.PagamentoDiaristaReqData
//import rest.data.PagamentoDiaristaResData
//import rest.security.Logado
//import java.math.BigDecimal
//import javax.inject.Inject
//import javax.ws.rs.GET
//import javax.ws.rs.Path
//import javax.ws.rs.Produces
//import javax.ws.rs.QueryParam
//
//@Path("pagamento/diaristas")
//class PagamentoDiaristaREST : PagamentoREST<PagamentoDiarista, TipoDespesaDiarista, PagamentoDiaristaReqData, PagamentoDiaristaResData, PagamentoDiaristaDAO, TipoDespesaDiaristaDAO>() {
//
//    @Inject
//    lateinit var dao2: PagamentoDiaristaDAO
//
//    @GET
//    @Logado
//    @Path("saldo")
//    @Produces("application/json")
//    fun saldo(@QueryParam("ano") ano: Int?, @QueryParam("mes") mes: Int?): BigDecimal {
////        valida(ano, mes)
//        lancarExcecaoSeNecessario()
//
//        return dao2.pagoAte(ano!!, mes!!) - dao2.devidoAte(ano, mes)
//    }
//}