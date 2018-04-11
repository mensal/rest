package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.PagamentoDiarista
import java.time.LocalDate
import java.util.*

@JsonPropertyOrder("id", "data", "tipo", "valores")
class PagamentoDiaristaResData : ResData<PagamentoDiarista> {

    var id: UUID? = null

    var data: LocalDate? = null

    var tipo: TipoDespesaDiaristaResData? = null

    var valores: List<UsuarioPagamentoResData>? = null

    override fun preencherCom(entidade: PagamentoDiarista?) {
        id = entidade?.id
        data = entidade?.data

        if (tipo == null) tipo = TipoDespesaDiaristaResData()
        tipo?.preencherCom(entidade?.tipo)

        if (valores == null) valores = mutableListOf()
        valores = entidade?.valores?.map {
            val data = UsuarioPagamentoResData()
            data.preencherCom(it)
            data
        }
    }
}
