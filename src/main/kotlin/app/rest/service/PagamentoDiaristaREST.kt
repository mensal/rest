package app.rest.service

import app.core.entity.PagamentoDiarista
import app.core.entity.TipoDespesaDiarista
import app.core.persistence.PagamentoDiaristaDAO
import app.core.persistence.TipoDespesaDiaristaDAO
import app.rest.data.PagamentoDiaristaReqData
import app.rest.data.PagamentoDiaristaResData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("api/pagamento/diaristas")
class PagamentoDiaristaREST : PagamentoREST<PagamentoDiarista, TipoDespesaDiarista, PagamentoDiaristaReqData, PagamentoDiaristaResData, PagamentoDiaristaDAO, TipoDespesaDiaristaDAO>() {

    @GetMapping("saldo")
    fun saldo(@RequestParam("ano") ano: Int?, @RequestParam("mes") mes: Int?): BigDecimal {
//        valida(ano, mes)
        lancarExcecaoSeNecessario()

        return dao.pagoAte(ano!!, mes!!) - dao.devidoAte(ano, mes)
    }
}