package app.rest.data

import app.core.entity.PagamentoCombustivel
import app.core.entity.TipoDespesaCombustivel
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.math.BigDecimal

@JsonPropertyOrder("id", "data", "odometro", "litros", "tipo", "valores")
class PagamentoCombustivelResData : PagamentoResData<PagamentoCombustivel, TipoDespesaCombustivel, TipoDespesaCombustivelResData>() {

    var odometro: Int? = null

    var litros: BigDecimal? = null

    override fun novoTipoDespesaResponseData() = TipoDespesaCombustivelResData()

    override fun preencherCom(entidade: PagamentoCombustivel?) {
        super.preencherCom(entidade)

//        if (excluidoEm == null) {
        odometro = entidade?.odometro
        litros = entidade?.litros
//        }
    }
}
