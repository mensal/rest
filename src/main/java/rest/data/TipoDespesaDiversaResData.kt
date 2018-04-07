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
        id = entidade?.id
        nome = entidade?.nome

        periodo = PeriodoResData()
        periodo?.preencherCom(entidade?.periodo)
    }
}