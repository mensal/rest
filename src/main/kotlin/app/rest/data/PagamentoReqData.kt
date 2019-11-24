//package app.rest.data
//
//import app.core.entity.Coordenada
//import app.core.entity.Pagamento
//import org.hibernate.validator.constraints.NotEmpty
//import java.time.LocalDate
//import javax.validation.Valid
//import javax.validation.constraints.NotNull
//
//abstract class PagamentoReqData<in E : Pagamento<*>> : ReqData<E> {
//
//    @NotNull
//    lateinit var data: LocalDate
//
//    @Valid
//    var coordenada: CoordenadaReqData? = null
//
//    @Valid
//    @NotNull
//    lateinit var tipo: IdReqData
//
//    @Valid
//    @NotEmpty
//    lateinit var valores: List<UsuarioPagamentoReqData>
//
//    override fun escreverEm(entidade: E) {
//        entidade.data = data
//
//        if (entidade.coordenada == null) {
//            entidade.coordenada = Coordenada()
//        }
//
//        coordenada?.escreverEm(entidade.coordenada!!)
//    }
//}
