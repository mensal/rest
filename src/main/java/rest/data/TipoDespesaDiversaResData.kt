package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.TipoDespesaDiversa
import java.util.*

@JsonPropertyOrder("id", "nome", "periodo")
class TipoDespesaDiversaResData : ResData<TipoDespesaDiversa> {

    var id: UUID? = null

    var nome: String? = null

    var periodo: PeriodoResData? = null

    override fun preencherCom(entidade: TipoDespesaDiversa?) {
        this.id = entidade?.id
        this.nome = entidade?.nome

        val periodo = PeriodoResData()
        periodo.preencherCom(entidade?.periodo)
        this.periodo = periodo
    }
}