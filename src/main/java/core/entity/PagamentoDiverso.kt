package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "pagamento_diverso")
open class PagamentoDiverso : Pagamento() {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipo_despesa")
    var tipoDespesa: TipoDespesaDiversa? = null

    var observacao: String? = null
}