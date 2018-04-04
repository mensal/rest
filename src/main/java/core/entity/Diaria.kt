package core.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "diaria")
open class Diaria {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @NotNull
    @Embedded
    var periodo: Periodo? = null

    @NotNull
    @Column(precision = 8, scale = 2)
    var valor: BigDecimal? = null
}