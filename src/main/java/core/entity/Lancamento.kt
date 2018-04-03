package core.entity

import java.util.*
import javax.persistence.*
import javax.persistence.InheritanceType.JOINED
import javax.persistence.TemporalType.TIMESTAMP
import javax.validation.constraints.NotNull

@Entity
@Inheritance(strategy = JOINED)
internal open class Lancamento {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @NotNull
    @Temporal(TIMESTAMP)
    @Column(columnDefinition = "timestamp with time zone")
    var data: Date? = null
}