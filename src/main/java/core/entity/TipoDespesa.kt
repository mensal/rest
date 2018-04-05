package core.entity

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@MappedSuperclass
abstract class TipoDespesa {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @NotNull
    @Embedded
    var periodo: Periodo? = null
}