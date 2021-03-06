package rest.data

import core.entity.PagamentoDiversa

class PagamentoDiversaReqData : PagamentoReqData<PagamentoDiversa>() {

    var observacao: String? = null

    override fun escreverEm(entidade: PagamentoDiversa) {
        super.escreverEm(entidade)

        entidade.observacao = observacao
    }
}
