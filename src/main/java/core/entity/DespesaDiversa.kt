package core.entity

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "despesa_diversa")
open class DespesaDiversa : Despesa()