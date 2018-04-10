package core.entity

import java.time.LocalDate
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
    var data: LocalDate? = null

    abstract var tipo: T?

//    constructor(id: UUID?, data: LocalDate) : this() {
//        this.id = id
//        this.data = data
//    }

    @Transient
    var valores: List<UsuarioPagamento>? = null
}