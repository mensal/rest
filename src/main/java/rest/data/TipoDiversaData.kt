package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.TipoDiversa
import java.util.*
import javax.validation.constraints.NotNull

@JsonPropertyOrder("id", "nome", "periodo")
class TipoDiversaData {

    var id: UUID? = null

    @NotNull
    var nome: String? = null

    var periodo: PeriodoData? = null

    fun ler(tipo: TipoDiversa?): TipoDiversaData {
        this.id = tipo?.id
        this.nome = tipo?.nome
        this.periodo = PeriodoData().ler(tipo?.periodo)

        return this
    }

    fun escrever(tipo: TipoDiversa?): TipoDiversa? {
        tipo?.id = this.id
        tipo?.nome = this.nome
        tipo?.periodo = periodo?.escrever(tipo?.periodo)

        return tipo
    }
}