package core.entity

import java.util.*
import javax.persistence.*

@Entity
open class Usuario {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @Version
    val versao: Long? = null
}