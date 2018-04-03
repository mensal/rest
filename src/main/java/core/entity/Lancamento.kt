package core.entity

import java.util.*
import javax.persistence.*
import javax.persistence.InheritanceType.JOINED

@Entity
@Inheritance(strategy = JOINED)
internal open class Lancamento {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null
}