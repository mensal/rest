//package app.rest.data
//
//import app.core.entity.TipoDespesa
//import java.util.*
//import javax.validation.constraints.NotNull
//
//class IdReqData : ReqData<TipoDespesa> {
//
//    @NotNull
//    lateinit var id: UUID
//
//    override fun escreverEm(entidade: TipoDespesa) {
//        entidade.id = id
//    }
//}