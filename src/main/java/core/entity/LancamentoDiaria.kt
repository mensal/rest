package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "lancamento_diaria")
open class LancamentoDiaria : Lancamento() {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipo_despesa")
    var tipoDespesa: TipoDespesaDiaria? = null
}