//package app.rest.data
//
//import app.core.entity.PagamentoCombustivel
//import java.math.BigDecimal
//import javax.validation.constraints.NotNull
//
//class PagamentoCombustivelReqData : PagamentoReqData<PagamentoCombustivel>() {
//
//    @NotNull
//    var odometro: Int? = null
//
//    @NotNull
//    lateinit var litros: BigDecimal
//
//    override fun escreverEm(entidade: PagamentoCombustivel) {
//        super.escreverEm(entidade)
//
//        entidade.odometro = odometro
//        entidade.litros = litros
//    }
//}
