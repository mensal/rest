package core.entity

import javax.persistence.*

@Entity
@Table(name = "lancamento_despesa_fixa")
internal class LancamentoDespesaFixa : Lancamento() {

    @ManyToOne
    @JoinColumn(name = "id_tipo_despesa_fixa")
    var tipo: TipoDespesaFixa? = null
}