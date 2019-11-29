package app.rest.data

import app.core.entity.PagamentoDiarista
import app.core.entity.TipoDespesaDiarista
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("id", "data", "tipo", "valores")
class PagamentoDiaristaResData : PagamentoResData<PagamentoDiarista, TipoDespesaDiarista, TipoDespesaDiaristaResData>() {

    override fun novoTipoDespesaResponseData() = TipoDespesaDiaristaResData()
}