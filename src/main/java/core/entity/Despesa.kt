package core.entity

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@MappedSuperclass
abstract class Despesa {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @NotNull
    @Embedded
    var periodo: Periodo? = null

    @NotNull
    var nome: String? = null
}