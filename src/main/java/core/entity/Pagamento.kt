package core.entity

import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType.JOINED
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "pagamento")
@Inheritance(strategy = JOINED)
open class Pagamento() : Versionado() {

    @NotNull
    var data: LocalDate? = null

    constructor(id: UUID?, data: LocalDate) : this() {
        this.id = id
        this.data = data
    }
}