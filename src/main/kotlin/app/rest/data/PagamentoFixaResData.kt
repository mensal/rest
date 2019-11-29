package app.rest.data

import app.core.entity.PagamentoFixa
import app.core.entity.TipoDespesaFixa
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("id", "data", "tipo", "valores")
class PagamentoFixaResData : PagamentoResData<PagamentoFixa, TipoDespesaFixa, TipoDespesaFixaResData>() {

    override fun novoTipoDespesaResponseData() = TipoDespesaFixaResData()
}
