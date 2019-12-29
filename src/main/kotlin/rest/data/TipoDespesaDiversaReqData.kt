package rest.data

import core.entity.TipoDespesaDiversa
import javax.validation.constraints.NotBlank

class TipoDespesaDiversaReqData : TipoDespesaReqData<TipoDespesaDiversa>() {

    @NotBlank
    lateinit var nome: String

    override fun escreverEm(entidade: TipoDespesaDiversa) {
        super.escreverEm(entidade)

        entidade.nome = nome
    }
}