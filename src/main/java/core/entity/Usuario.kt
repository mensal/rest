package core.entity

import org.hibernate.validator.constraints.Email
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "usuario")
open class Usuario() {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @NotNull
    var nome: String? = null

    @Email
    @NotNull
    var email: String? = null

    constructor(id: UUID) : this() {
        this.id = id
    }
}