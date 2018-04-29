package rest.service

import core.entity.PagamentoDiarista
import core.entity.TipoDespesaDiarista
import core.persistence.PagamentoDiaristaDAO
import core.persistence.TipoDespesaDiaristaDAO
import rest.data.PagamentoDiaristaReqData
import rest.data.PagamentoDiaristaResData
import java.math.BigDecimal
import java.util.*
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("pagamento/diaristas")
open class PagamentoDiaristaREST : PagamentoREST<PagamentoDiarista, TipoDespesaDiarista, PagamentoDiaristaReqData, PagamentoDiaristaResData, PagamentoDiaristaDAO, TipoDespesaDiaristaDAO>() {

    @GET
    @Path("saldo")
    @Produces("application/json")
    open fun saldo(): BigDecimal {
        return BigDecimal(Random().nextDouble())
    }
}