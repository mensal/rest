package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.PagamentoDiversa
import core.entity.TipoDespesaDiversa

@JsonPropertyOrder("id", "data", "observacao", "tipo", "valores")
class PagamentoDiversaResData : PagamentoResData<PagamentoDiversa, TipoDespesaDiversa, TipoDespesaDiversaResData>() {

    var observacao: String? = null

    override fun preencherCom(entidade: PagamentoDiversa?) {
        super.preencherCom(entidade)

        observacao = entidade?.observacao
    }

    override fun novoTipoDespesaResponseData() = TipoDespesaDiversaResData()
}
