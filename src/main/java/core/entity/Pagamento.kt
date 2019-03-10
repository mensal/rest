package core.entity

import java.time.LocalDate
import java.time.YearMonth
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType.JOINED
import javax.persistence.Table
import javax.persistence.Transient
import javax.validation.constraints.NotNull

@Entity
@Table(name = "pagamento")
@Inheritance(strategy = JOINED)
abstract class Pagamento<T : TipoDespesa> : Versionado() {

    @NotNull
    open var data: LocalDate? = null

    abstract var tipo: T?

    @Transient
    open var valores: List<UsuarioPagamento>? = null

    companion object {
        fun primeiroDia(ano: Int, mes: Int) = LocalDate.of(ano, mes, 1)!!

        fun ultimoDia(ano: Int, mes: Int) = LocalDate.of(ano, mes, YearMonth.of(ano, mes).lengthOfMonth())!!
    }
}