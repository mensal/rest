package core.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.persistence.TemporalType.DATE
import javax.validation.constraints.NotNull

@Entity
internal class Diaria {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @NotNull
    @Temporal(DATE)
    var desde: Date? = null

    @Temporal(DATE)
    @Column
    var ate: Date? = null

    @NotNull
    @Column(precision = 8, scale = 2)
    var valor: BigDecimal? = null
}