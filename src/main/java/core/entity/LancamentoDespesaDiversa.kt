package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "lancamento_despesa_diversa")
open class LancamentoDespesaDiversa : Lancamento() {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_despesa_diversa")
    var despesa: DespesaDiversa? = null

    var observacao: String? = null
}