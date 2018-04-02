package core.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "teste")
internal class Teste {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null
}