package core.entity

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tipo_despesa_diarista")
open class TipoDespesaDiarista : TipoDespesa() {

    @NotNull
    @Column(precision = 8, scale = 2)
    open var valor: BigDecimal? = null
}