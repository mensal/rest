package app.core.entity

import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "pagamento_combustivel")
open class PagamentoCombustivel : Pagamento<TipoDespesaCombustivel>() {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipo")
    override var tipo: TipoDespesaCombustivel? = null

    open var odometro: Int? = null

    @Column(precision = 6, scale = 3)
    open var litros: BigDecimal? = null
}