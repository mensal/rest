package core.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tipo_diverso")
internal class TipoDiverso {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null
}