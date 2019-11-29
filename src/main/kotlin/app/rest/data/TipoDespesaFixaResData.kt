package app.rest.data

import app.core.entity.TipoDespesaFixa
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("id", "nome", "vencimento", "periodo")
class TipoDespesaFixaResData : TipoDespesaResData<TipoDespesaFixa>() {

    var nome: String? = null

    var vencimento: Int? = null

    override fun preencherCom(entidade: TipoDespesaFixa?) {
        super.preencherCom(entidade)

//        if (excluidoEm == null) {
        nome = entidade?.nome
        vencimento = entidade?.vencimento
//        }
    }
}