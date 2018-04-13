package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.PagamentoCombustivel
import core.entity.TipoDespesaCombustivel

@JsonPropertyOrder("id", "data", "tipo", "valores")
class PagamentoCombustivelResData : PagamentoResData<PagamentoCombustivel, TipoDespesaCombustivel, TipoDespesaCombustivelResData>() {

    override fun novoTipoDespesaResponseData() = TipoDespesaCombustivelResData()
}
