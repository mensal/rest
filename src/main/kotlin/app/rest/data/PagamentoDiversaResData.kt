package app.rest.data

import app.core.entity.PagamentoDiversa
import app.core.entity.TipoDespesaDiversa
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("id", "data", "observacao", "tipo", "valores")
class PagamentoDiversaResData : PagamentoResData<PagamentoDiversa, TipoDespesaDiversa, TipoDespesaDiversaResData>() {

    var observacao: String? = null

    override fun novoTipoDespesaResponseData() = TipoDespesaDiversaResData()

    override fun preencherCom(entidade: PagamentoDiversa?) {
        super.preencherCom(entidade)

//        if (excluidoEm == null) {
        observacao = entidade?.observacao
//        }
    }
}
