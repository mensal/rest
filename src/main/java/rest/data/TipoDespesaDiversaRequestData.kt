package rest.data

import core.entity.Periodo
import core.entity.TipoDespesaDiversa
import javax.validation.constraints.NotNull

class TipoDespesaDiversaRequestData : RequestData<TipoDespesaDiversa> {

    @NotNull
    lateinit var nome: String

    @NotNull
    lateinit var periodo: PeriodoRequestData

    override fun escrever(entidade: TipoDespesaDiversa?): TipoDespesaDiversa? {
        entidade?.nome = this.nome
        entidade?.periodo = periodo.escrever(entidade?.periodo ?: Periodo())

        return entidade
    }
}