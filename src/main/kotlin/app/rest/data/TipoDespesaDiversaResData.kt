package app.rest.data

import app.core.entity.TipoDespesaDiversa
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("id", "nome", "periodo")
class TipoDespesaDiversaResData : TipoDespesaResData<TipoDespesaDiversa>() {

    var nome: String? = null

    override fun preencherCom(entidade: TipoDespesaDiversa?) {
        super.preencherCom(entidade)

//        if (excluidoEm == null) {
        nome = entidade?.nome
//        }
    }
}