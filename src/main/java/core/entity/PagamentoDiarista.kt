package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "pagamento_diarista")
open class PagamentoDiarista : Pagamento() {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipo")
    var tipo: TipoDiarista? = null
}