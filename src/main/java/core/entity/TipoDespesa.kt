package core.entity

import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType.JOINED
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tipo_despesa")
@Inheritance(strategy = JOINED)
abstract class TipoDespesa : Versionado() {

    @NotNull
    @Embedded
    var periodo: Periodo? = null
}