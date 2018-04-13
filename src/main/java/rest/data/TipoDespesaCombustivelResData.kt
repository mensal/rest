package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.TipoDespesaCombustivel

@JsonPropertyOrder("id", "veiculo", "periodo")
class TipoDespesaCombustivelResData : TipoDespesaResData<TipoDespesaCombustivel>() {

    var veiculo: String? = null

    override fun preencherCom(entidade: TipoDespesaCombustivel?) {
        super.preencherCom(entidade)

        veiculo = entidade?.veiculo
    }
}