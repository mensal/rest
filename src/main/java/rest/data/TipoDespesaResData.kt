package rest.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.TipoDespesa
import java.time.ZonedDateTime
import java.util.*

@JsonPropertyOrder("id", "periodo", "atualizado_em")
abstract class TipoDespesaResData<in E : TipoDespesa> : ResData<E> {

    var id: UUID? = null

    @JsonProperty("atualizado_em")
    var atualizadoEm: ZonedDateTime? = null

//    var periodo: PeriodoResData? = null

    override fun preencherCom(entidade: E?) {
        id = entidade?.id

//        if (periodo == null) periodo = PeriodoResData()
//        periodo!!.preencherCom(entidade?.periodo)

        atualizadoEm = entidade?.atualizadoEm
    }
}