package core.entity

import java.util.*
import javax.persistence.*

@Entity
internal class Diaria {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null
}