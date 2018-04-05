package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "lancamento_diverso")
open class LancamentoDiverso : Lancamento() {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipo_despesa")
    var tipoDespesa: TipoDespesaDiversa? = null

    var observacao: String? = null
}