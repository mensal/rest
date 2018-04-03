package core.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tipo_fixo")
internal class TipoFixo {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null
}