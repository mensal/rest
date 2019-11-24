//package app.rest.service
//
//import app.core.entity.PagamentoDiarista
//import app.core.entity.TipoDespesaDiarista
//import app.core.persistence.PagamentoDiaristaDAO
//import app.core.persistence.TipoDespesaDiaristaDAO
//import app.rest.data.PagamentoDiaristaReqData
//import app.rest.data.PagamentoDiaristaResData
//import app.rest.security.Logado
//import java.math.BigDecimal
//import javax.ws.rs.GET
//import javax.ws.rs.Path
//import javax.ws.rs.Produces
//import javax.ws.rs.QueryParam
//
//@Path("pagamento/diaristas")
//open class PagamentoDiaristaREST : PagamentoREST<PagamentoDiarista, TipoDespesaDiarista, PagamentoDiaristaReqData, PagamentoDiaristaResData, PagamentoDiaristaDAO, TipoDespesaDiaristaDAO>() {
//
//    @GET
//    @Logado
//    @Path("saldo")
//    @Produces("application/json")
//    open fun saldo(@QueryParam("ano") ano: Int?, @QueryParam("mes") mes: Int?): BigDecimal {
////        valida(ano, mes)
//        lancarExcecaoSeNecessario()
//
//        return dao.pagoAte(ano!!, mes!!) - dao.devidoAte(ano, mes)
//    }
//}