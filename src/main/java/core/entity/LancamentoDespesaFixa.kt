package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "lancamento_despesa_fixa")
open class LancamentoDespesaFixa : Lancamento() {

    @ManyToOne
    @JoinColumn(name = "id_despesa_fixa")
    var despesa: DespesaFixa? = null
}