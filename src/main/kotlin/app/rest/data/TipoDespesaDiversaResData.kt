//package app.rest.data
//
//import com.fasterxml.jackson.annotation.JsonPropertyOrder
//import app.core.entity.TipoDespesaDiversa
//
//@JsonPropertyOrder("id", "nome", "periodo")
//class TipoDespesaDiversaResData : TipoDespesaResData<TipoDespesaDiversa>() {
//
//    var nome: String? = null
//
//    override fun preencherCom(entidade: TipoDespesaDiversa?) {
//        super.preencherCom(entidade)
//
////        if (excluidoEm == null) {
//        nome = entidade?.nome
////        }
//    }
//}