//package app.core.entity
//
//import java.time.LocalDate
//import java.time.YearMonth
//import javax.persistence.*
//import javax.persistence.InheritanceType.JOINED
//import javax.validation.constraints.NotNull
//
//@Entity
//@Table(name = "pagamento")
//@Inheritance(strategy = JOINED)
//abstract class Pagamento<T : TipoDespesa> : Versionado() {
//
//    @NotNull
//    open var data: LocalDate? = null
//
//    @NotNull
//    @Embedded
//    var coordenada: Coordenada? = null
//
//    abstract var tipo: T?
//
//    @Transient
//    open var valores: List<UsuarioPagamento>? = null
//
//    companion object {
//        fun primeiroDia(ano: Int, mes: Int) = LocalDate.of(ano, mes, 1)!!
//
//        fun ultimoDia(ano: Int, mes: Int) = LocalDate.of(ano, mes, YearMonth.of(ano, mes).lengthOfMonth())!!
//    }
//}