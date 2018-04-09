package core.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "pagamento_diversa")
open class PagamentoDiversa : Pagamento(), PagamentoComTipoDespesa<TipoDespesaDiversa> {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipo")
    override var tipo: TipoDespesaDiversa? = null

    var observacao: String? = null

    @Transient
    var valores: List<UsuarioPagamento>? = null
}