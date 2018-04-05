package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.TipoDespesaDiversa
import java.util.*
import javax.validation.constraints.NotNull

@JsonPropertyOrder("id", "nome", "periodo")
class DespesaDiversaData {

    var id: UUID? = null

    @NotNull
    var nome: String? = null

    var periodo: PeriodoData? = null

    fun ler(despesa: TipoDespesaDiversa?): DespesaDiversaData {
        this.id = despesa?.id
        this.nome = despesa?.nome
        this.periodo = PeriodoData().ler(despesa?.periodo)

        return this
    }

    fun escrever(despesa: TipoDespesaDiversa?): TipoDespesaDiversa? {
        despesa?.id = this.id
        despesa?.nome = this.nome
        despesa?.periodo = periodo?.escrever(despesa?.periodo)

        return despesa
    }
}