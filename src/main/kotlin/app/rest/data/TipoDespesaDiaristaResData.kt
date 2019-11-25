package app.rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import app.core.entity.TipoDespesaDiarista
import java.math.BigDecimal

@JsonPropertyOrder("id", "valor", "periodo")
class TipoDespesaDiaristaResData : TipoDespesaResData<TipoDespesaDiarista>() {

    var valor: BigDecimal? = null

    override fun preencherCom(entidade: TipoDespesaDiarista?) {
        super.preencherCom(entidade)

//        if (excluidoEm == null) {
        valor = entidade?.valor
//        }
    }
}