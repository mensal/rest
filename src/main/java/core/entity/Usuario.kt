package core.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "usuario")
open class Usuario {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null
}