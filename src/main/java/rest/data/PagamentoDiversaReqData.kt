package rest.data

import core.entity.PagamentoDiversa
import core.entity.TipoDespesaDiversa

class PagamentoDiversaReqData : PagamentoReqData<PagamentoDiversa, TipoDespesaDiversa>() {

    var observacao: String? = null

    override fun escreverEm(entidade: PagamentoDiversa) {
        super.escreverEm(entidade)

        entidade.observacao = observacao
    }
}
