package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "lancamento_abastecimento")
open class LancamentoAbastecimento : Lancamento() {

    @ManyToOne
    @JoinColumn(name = "id_automovel")
    var automovel: Automovel? = null

    var odometro: Int? = null

    var litros: Int? = null
}