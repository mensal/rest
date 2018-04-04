package core.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tipo_despesa_diversa")
open class TipoDespesaDiversa {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null
}