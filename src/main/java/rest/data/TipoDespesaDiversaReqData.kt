package rest.data

import core.entity.Periodo
import core.entity.TipoDespesaDiversa
import javax.validation.constraints.NotNull

class TipoDespesaDiversaReqData : ReqData<TipoDespesaDiversa> {

    @NotNull
    lateinit var nome: String

    @NotNull
    lateinit var periodo: PeriodoReqData

    override fun escreverEm(entidade: TipoDespesaDiversa?) {
        entidade?.nome = this.nome

        entidade?.periodo = entidade?.periodo ?: Periodo()
        periodo.escreverEm(entidade?.periodo)
        entidade?.periodo = entidade?.periodo
    }
}