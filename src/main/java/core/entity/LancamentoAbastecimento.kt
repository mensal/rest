package core.entity

import javax.persistence.*

@Entity
@Table(name = "lancamento_abastecimento")
internal class LancamentoAbastecimento : Lancamento() {

    @ManyToOne
    @JoinColumn(name = "id_automovel")
    var automovel: Automovel? = null

    var odometro: Int? = null

    var litros: Int? = null
}