package rest.data

import core.entity.TipoDespesaFixa
import javax.validation.constraints.NotNull

class TipoDespesaFixaReqData : TipoDespesaReqData<TipoDespesaFixa>() {

    @NotNull
    var vencimento: Int? = null

    override fun escreverEm(entidade: TipoDespesaFixa) {
        super.escreverEm(entidade)

        entidade.vencimento = vencimento
    }
}