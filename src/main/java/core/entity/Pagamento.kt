package core.entity

import java.util.*
import javax.persistence.*
import javax.persistence.InheritanceType.JOINED
import javax.validation.constraints.NotNull

@Entity
@Table(name = "pagamento")
@Inheritance(strategy = JOINED)
open class Pagamento : Versionado() {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @NotNull
    @Temporal(TemporalType.DATE)
    var data: Date? = null

}