package core.entity

import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tipo_despesa_fixa")
open class TipoDespesaFixa : TipoDespesa() {

    @NotNull
    var nome: String? = null

    @NotNull
    var vencimento: Int? = null
}