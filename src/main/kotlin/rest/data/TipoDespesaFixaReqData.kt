package rest.data

import core.entity.TipoDespesaFixa
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class TipoDespesaFixaReqData : TipoDespesaReqData<TipoDespesaFixa>() {

    @NotBlank
    lateinit var nome: String

    @NotNull
    var vencimento: Int? = null

    override fun escreverEm(entidade: TipoDespesaFixa) {
        super.escreverEm(entidade)

        entidade.nome = nome
        entidade.vencimento = vencimento
    }
}