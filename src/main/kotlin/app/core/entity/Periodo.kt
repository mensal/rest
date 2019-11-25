package app.core.entity

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
open class Periodo {

    @NotNull
    @Column(name = "periodo_de")
    var de: LocalDate? = null

    @Column(name = "periodo_ate")
    var ate: LocalDate? = null
}