package core.entity

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

    var odometro: Int? = null

    @Column(scale = 6, precision = 3)
    var litros: BigDecimal? = null
}