package core.entity

import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "despesa_fixa")
open class DespesaFixa : Despesa() {

    @NotNull
    var vencimento: Int? = null
}