package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.Periodo
import core.entity.TipoDespesaDiversa
import java.util.*
import javax.validation.constraints.NotNull

@JsonPropertyOrder("id", "nome", "periodo")
class TipoDespesaDiversaData : Data<TipoDespesaDiversa, TipoDespesaDiversaData> {

    var id: UUID? = null

    @NotNull
    var nome: String? = null

    @NotNull
    var periodo: PeriodoData? = null

    override fun ler(entidade: TipoDespesaDiversa?): TipoDespesaDiversaData {
        this.id = entidade?.id
        this.nome = entidade?.nome
        this.periodo = PeriodoData().ler(entidade?.periodo)

        return this
    }

    override fun escrever(entidade: TipoDespesaDiversa?): TipoDespesaDiversa? {
        entidade?.id = this.id
        entidade?.nome = this.nome
        entidade?.periodo = periodo?.escrever(entidade?.periodo ?: Periodo())

        return entidade
    }
}