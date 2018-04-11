package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.TipoDespesaDiarista
import java.math.BigDecimal
import java.util.*

@JsonPropertyOrder("id", "nome", "periodo")
class TipoDespesaDiaristaResData : ResData<TipoDespesaDiarista> {

    var id: UUID? = null

    var valor: BigDecimal? = null

    var periodo: PeriodoResData? = null

    override fun preencherCom(entidade: TipoDespesaDiarista?) {
        id = entidade?.id
        valor = entidade?.valor

        if (periodo == null) periodo = PeriodoResData()
        periodo!!.preencherCom(entidade?.periodo)
    }
}