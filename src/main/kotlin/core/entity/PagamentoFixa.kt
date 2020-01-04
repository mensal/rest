package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "pagamento_fixa")
open class PagamentoFixa : Pagamento<TipoDespesaFixa>() {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipo")
    override var tipo: TipoDespesaFixa? = null
}