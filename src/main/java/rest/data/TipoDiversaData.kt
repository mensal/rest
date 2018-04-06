package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.TipoDespesaDiversa
import java.util.*
import javax.validation.constraints.NotNull

@JsonPropertyOrder("id", "nome", "periodo")
class TipoDiversaData : Data<TipoDespesaDiversa> {

    var id: UUID? = null

    @NotNull
    var nome: String? = null

    var periodo: PeriodoData? = null

    override fun ler(tipo: TipoDespesaDiversa?): TipoDiversaData {
        this.id = tipo?.id
        this.nome = tipo?.nome
        this.periodo = PeriodoData().ler(tipo?.periodo)

        return this
    }

    override fun escrever(tipo: TipoDespesaDiversa?): TipoDespesaDiversa? {
        tipo?.id = this.id
        tipo?.nome = this.nome
        tipo?.periodo = periodo?.escrever(tipo?.periodo)

        return tipo
    }
}