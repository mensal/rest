package core.entity

import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tipo_despesa_diversa")
open class TipoDiversa : Tipo() {

    @NotNull
    var nome: String? = null
}