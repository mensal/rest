package core.entity

import java.util.*
import javax.persistence.*
import javax.persistence.InheritanceType.JOINED
import javax.validation.constraints.NotNull

@Entity
@Table(name = "pagamento")
@Inheritance(strategy = JOINED)
open class Pagamento : Versionado() {

    @NotNull
    @Temporal(TemporalType.DATE)
    var data: Date? = null
}