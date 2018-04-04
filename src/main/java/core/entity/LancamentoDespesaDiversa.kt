package core.entity

import javax.persistence.*

@Entity
@Table(name = "lancamento_despesa_diversa")
internal class LancamentoDespesaDiversa : Lancamento() {

    @ManyToOne
    @JoinColumn(name = "id_tipo_despesa_diversa")
    var tipo: TipoDespesaDiversa? = null
}