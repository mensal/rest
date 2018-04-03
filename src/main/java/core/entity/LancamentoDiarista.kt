package core.entity

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "lancamento_diarista")
internal class LancamentoDiarista : Lancamento() {

}