package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.TipoDespesaDiversa
import java.util.*

@JsonPropertyOrder("id", "nome", "periodo")
class TipoDespesaDiversaResponseData : ResponseData<TipoDespesaDiversa, TipoDespesaDiversaResponseData> {

    var id: UUID? = null

    var nome: String? = null

    var periodo: PeriodoResponseData? = null

    override fun ler(entidade: TipoDespesaDiversa?): TipoDespesaDiversaResponseData {
        this.id = entidade?.id
        this.nome = entidade?.nome
        this.periodo = PeriodoResponseData().ler(entidade?.periodo)

        return this
    }
}