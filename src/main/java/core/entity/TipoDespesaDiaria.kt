package core.entity

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tipo_despesa_diaria")
open class TipoDespesaDiaria : TipoDespesa() {

    @NotNull
    @Column(precision = 8, scale = 2)
    var valor: BigDecimal? = null
}