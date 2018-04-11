package rest.data

import core.entity.Periodo
import core.entity.TipoDespesa
import javax.validation.constraints.NotNull

abstract class TipoDespesaReqData<E : TipoDespesa> : ReqData<E> {

    @NotNull
    lateinit var periodo: PeriodoReqData

    override fun escreverEm(entidade: E) {
        if (entidade.periodo == null) entidade.periodo = Periodo()
        periodo.escreverEm(entidade.periodo!!)
    }
}