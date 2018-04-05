package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "lancamento_abastecimento")
open class LancamentoAbastecimento : Lancamento() {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipo_despesa")
    var tipoDespesa: TipoDespesaAutomovel? = null

    var odometro: Int? = null

    var litros: Int? = null
}