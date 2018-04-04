package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "lancamento_despesa_fixa")
open class LancamentoDespesaFixa : Lancamento() {

    @ManyToOne
    @JoinColumn(name = "id_tipo_despesa_fixa")
    var tipo: TipoDespesaFixa? = null
}