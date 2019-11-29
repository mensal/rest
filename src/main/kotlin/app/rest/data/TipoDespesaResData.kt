package app.rest.data

import app.core.entity.TipoDespesa
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.time.ZonedDateTime
import java.util.*

@JsonPropertyOrder("id", "periodo", "atualizado_em", "excluido_em")
abstract class TipoDespesaResData<in E : TipoDespesa> : ResData<E> {

    var id: UUID? = null

    @JsonProperty("atualizado_em")
    var atualizadoEm: ZonedDateTime? = null

    @JsonProperty("excluido_em")
    var excluidoEm: ZonedDateTime? = null

//    var periodo: PeriodoResData? = null

    override fun preencherCom(entidade: E?) {
        id = entidade?.id
        excluidoEm = entidade?.excluidoEm

//        if (excluidoEm == null) {
//        if (periodo == null) periodo = PeriodoResData()
//        periodo!!.preencherCom(entidade?.periodo)

        atualizadoEm = entidade?.atualizadoEm
    }
//    }
}