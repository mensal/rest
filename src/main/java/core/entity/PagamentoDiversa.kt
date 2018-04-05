package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "pagamento_diversa")
open class PagamentoDiversa : Pagamento() {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipo")
    var tipo: TipoDiversa? = null

    var observacao: String? = null
}