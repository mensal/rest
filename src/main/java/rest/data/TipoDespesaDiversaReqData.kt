package rest.data

import core.entity.Periodo
import core.entity.TipoDespesaDiversa
import javax.validation.constraints.NotNull

class TipoDespesaDiversaReqData : ReqData<TipoDespesaDiversa> {

    @NotNull
    lateinit var nome: String

    @NotNull
    lateinit var periodo: PeriodoReqData

    override fun escreverEm(entidade: TipoDespesaDiversa) {
        entidade.nome = nome
        if (entidade.periodo == null) entidade.periodo = Periodo()
        periodo.escreverEm(entidade.periodo!!)
    }
}