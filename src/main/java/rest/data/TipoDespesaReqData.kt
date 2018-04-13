package rest.data

import core.entity.TipoDespesa

abstract class TipoDespesaReqData<E : TipoDespesa> : ReqData<E> {

//    @NotNull
//    lateinit var periodo: PeriodoReqData

    override fun escreverEm(entidade: E) {
//        if (entidade.periodo == null) entidade.periodo = Periodo()
//        periodo.escreverEm(entidade.periodo!!)
    }
}