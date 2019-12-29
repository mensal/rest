package rest.data

import core.entity.TipoDespesaCombustivel
import javax.validation.constraints.NotBlank

class TipoDespesaCombustivelReqData : TipoDespesaReqData<TipoDespesaCombustivel>() {

    @NotBlank
    lateinit var veiculo: String

    override fun escreverEm(entidade: TipoDespesaCombustivel) {
        super.escreverEm(entidade)

        entidade.veiculo = veiculo
    }
}