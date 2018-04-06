package core.entity

import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tipo_despesa_combustivel")
open class TipoDespesaCombustivel : TipoDespesa() {

    @NotNull
    var veiculo: String? = null
}