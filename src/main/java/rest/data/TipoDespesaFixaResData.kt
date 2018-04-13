package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.TipoDespesaFixa

@JsonPropertyOrder("id", "vencimento", "periodo")
class TipoDespesaFixaResData : TipoDespesaResData<TipoDespesaFixa>() {

    var vencimento: Int? = null

    override fun preencherCom(entidade: TipoDespesaFixa?) {
        super.preencherCom(entidade)

        vencimento = entidade?.vencimento
    }
}