package core.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Temporal
import javax.persistence.TemporalType.DATE
import javax.validation.constraints.NotNull

@Embeddable
open class Periodo {

    @NotNull
    @Temporal(DATE)
    @Column(name = "periodo_de")
    var de: Date? = null

    @Temporal(DATE)
    @Column(name = "periodo_ate")
    var ate: Date? = null
}