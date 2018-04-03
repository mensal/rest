package core.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "lancamento_fixo")
internal class LancamentoFixo : Lancamento() {

    @ManyToOne
    @JoinColumn(name = "id_tipo_fixo")
    var tipo: TipoFixo? = null
}