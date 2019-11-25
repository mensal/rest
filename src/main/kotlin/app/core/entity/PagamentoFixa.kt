package app.core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "pagamento_fixa")
open class PagamentoFixa : Pagamento<TipoDespesaFixa>() {

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    override var tipo: TipoDespesaFixa? = null
}