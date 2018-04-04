package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "lancamento_despesa_diversa")
internal class LancamentoDespesaDiversa : Lancamento() {

    @ManyToOne
    @JoinColumn(name = "id_tipo_despesa_diversa")
    var tipo: TipoDespesaDiversa? = null
}