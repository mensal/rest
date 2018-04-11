package rest.data

import core.entity.Periodo
import core.entity.TipoDespesaDiarista
import java.math.BigDecimal
import javax.validation.constraints.NotNull

class TipoDespesaDiaristaReqData : ReqData<TipoDespesaDiarista> {

    @NotNull
    lateinit var valor: BigDecimal

    @NotNull
    lateinit var periodo: PeriodoReqData

    override fun escreverEm(entidade: TipoDespesaDiarista) {
        entidade.valor = valor

        if (entidade.periodo == null) entidade.periodo = Periodo()
        periodo.escreverEm(entidade.periodo!!)
    }
}