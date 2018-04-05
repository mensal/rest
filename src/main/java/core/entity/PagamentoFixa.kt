package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "pagamento_fixa")
open class PagamentoFixa : Pagamento() {

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    var tipo: TipoFixa? = null
}