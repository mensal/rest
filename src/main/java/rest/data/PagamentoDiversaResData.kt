package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.PagamentoDiversa
import java.time.LocalDate
import java.util.*

@JsonPropertyOrder("id", "data", "observacao", "tipo")
class PagamentoDiversaResData : ResData<PagamentoDiversa> {

    var id: UUID? = null

    var data: LocalDate? = null

    var observacao: String? = null

    var tipo: TipoDespesaDiversaResData? = null

    var valores: List<UsuarioPagamentoResData>? = null

    override fun preencherCom(entidade: PagamentoDiversa?) {
        id = entidade?.id
        data = entidade?.data
        observacao = entidade?.observacao

        if (tipo == null) tipo = TipoDespesaDiversaResData()
        tipo?.preencherCom(entidade?.tipo)

        if (valores == null) valores = mutableListOf()
//        valores = entidade.valores.map {  }
    }
}
