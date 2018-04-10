package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "pagamento_diarista")
open class PagamentoDiarista : Pagamento<TipoDespesaDiarista>(), PagamentoComTipoDespesa<TipoDespesaDiarista> {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipo")
    override var tipo: TipoDespesaDiarista? = null
}