package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "pagamento_combustivel")
open class PagamentoCombustivel : Pagamento<TipoDespesaCombustivel>() {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipo")
    override var tipo: TipoDespesaCombustivel? = null

    var odometro: Int? = null

    var litros: Int? = null
}