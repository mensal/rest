package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.TipoDespesaDiversa

@JsonPropertyOrder("id", "nome", "periodo")
class TipoDespesaDiversaResData : TipoDespesaResData<TipoDespesaDiversa>() {

    var nome: String? = null

    override fun preencherCom(entidade: TipoDespesaDiversa?) {
        super.preencherCom(entidade)

        nome = entidade?.nome
    }
}