package rest.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.Pagamento
import core.entity.TipoDespesa
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*

@JsonPropertyOrder("id", "data", "tipo", "valores", "coordenada", "atualizado_em", "excluido_em")
abstract class PagamentoResData<in E : Pagamento<T>, T : TipoDespesa, R : TipoDespesaResData<T>> : Responsed<E> {

    var id: UUID? = null

    var data: LocalDate? = null

    var tipo: R? = null

    var valores: List<UsuarioPagamentoResData>? = null

    var coordenada: CoordenadaResData? = null

    @JsonProperty("atualizado_em")
    var atualizadoEm: ZonedDateTime? = null

    @JsonProperty("excluido_em")
    var excluidoEm: ZonedDateTime? = null

    abstract fun novoTipoDespesaResponseData(): R

    override fun preencherCom(entidade: E?) {
        id = entidade?.id
        excluidoEm = entidade?.excluidoEm

//        if (excluidoEm == null) {
        data = entidade?.data

        if (tipo == null) tipo = novoTipoDespesaResponseData()
        tipo?.preencherCom(entidade?.tipo)

        if (valores == null) valores = mutableListOf()
        valores = entidade?.valores?.map {
            val data = UsuarioPagamentoResData()
            data.preencherCom(it)
            data
        }

        coordenada?.preencherCom(entidade?.coordenada)
        atualizadoEm = entidade?.atualizadoEm
//        }
    }
}
