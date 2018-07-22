package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.TipoDespesaFixa

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