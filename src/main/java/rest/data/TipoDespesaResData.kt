package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.TipoDespesa
import java.util.*

@JsonPropertyOrder("id", "periodo")
abstract class TipoDespesaResData<in E : TipoDespesa> : ResData<E> {

    var id: UUID? = null

    var periodo: PeriodoResData? = null

    override fun preencherCom(entidade: E?) {
        id = entidade?.id

        if (periodo == null) periodo = PeriodoResData()
        periodo!!.preencherCom(entidade?.periodo)
    }
}