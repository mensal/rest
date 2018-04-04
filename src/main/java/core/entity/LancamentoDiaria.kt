package core.entity

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "lancamento_diaria")
internal class LancamentoDiaria : Lancamento() {
}