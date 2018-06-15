package rest.service

import core.entity.PagamentoDiarista
import core.entity.TipoDespesaDiarista
import core.persistence.PagamentoDiaristaDAO
import core.persistence.TipoDespesaDiaristaDAO
import rest.data.PagamentoDiaristaReqData
import rest.data.PagamentoDiaristaResData
import rest.security.Logado
import java.math.BigDecimal
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam

@Path("pagamento/diaristas")
open class PagamentoDiaristaREST : PagamentoREST<PagamentoDiarista, TipoDespesaDiarista, PagamentoDiaristaReqData, PagamentoDiaristaResData, PagamentoDiaristaDAO, TipoDespesaDiaristaDAO>() {

    @GET
    @Logado
    @Path("saldo")
    @Produces("application/json")
    open fun saldo(@QueryParam("ano") ano: Int?, @QueryParam("mes") mes: Int?): BigDecimal {
//        valida(ano, mes)
        lancarExcecaoSeNecessario()

        return dao.pagoAte(ano!!, mes!!) - dao.devidoAte(ano, mes)
    }
}