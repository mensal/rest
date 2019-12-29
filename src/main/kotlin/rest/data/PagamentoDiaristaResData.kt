package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.PagamentoDiarista
import core.entity.TipoDespesaDiarista

@JsonPropertyOrder("id", "data", "tipo", "valores")
class PagamentoDiaristaResData : PagamentoResData<PagamentoDiarista, TipoDespesaDiarista, TipoDespesaDiaristaResData>() {

    override fun novoTipoDespesaResponseData() = TipoDespesaDiaristaResData()
}