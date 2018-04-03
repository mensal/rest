package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "lancamento_diverso")
internal class LancamentoDiverso : Lancamento() {

    @ManyToOne
    @JoinColumn(name = "id_tipo_diverso")
    var tipo: TipoDiverso? = null
}