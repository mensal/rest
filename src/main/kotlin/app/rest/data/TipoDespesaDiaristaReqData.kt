package app.rest.data

import app.core.entity.TipoDespesaDiarista
import java.math.BigDecimal
import javax.validation.constraints.NotNull

class TipoDespesaDiaristaReqData : TipoDespesaReqData<TipoDespesaDiarista>() {

    @NotNull
    lateinit var valor: BigDecimal

    override fun escreverEm(entidade: TipoDespesaDiarista) {
        super.escreverEm(entidade)

        entidade.valor = valor
    }
}