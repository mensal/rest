package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.TipoDespesaDiversa
import java.util.*

@JsonPropertyOrder("id", "nome", "periodo")
class TipoDespesaDiversaResData : ResData<TipoDespesaDiversa, TipoDespesaDiversaResData> {

    var id: UUID? = null

    var nome: String? = null

    var periodo: PeriodoResData? = null

    override fun ler(entidade: TipoDespesaDiversa?): TipoDespesaDiversaResData {
        this.id = entidade?.id
        this.nome = entidade?.nome
        this.periodo = PeriodoResData().ler(entidade?.periodo)

        return this
    }
}