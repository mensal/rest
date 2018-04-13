package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.PagamentoFixa
import core.entity.TipoDespesaFixa

@JsonPropertyOrder("id", "data", "tipo", "valores")
class PagamentoFixaResData : PagamentoResData<PagamentoFixa, TipoDespesaFixa, TipoDespesaFixaResData>() {

    override fun novoTipoDespesaResponseData() = TipoDespesaFixaResData()
}
