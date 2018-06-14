package core.entity

import org.hibernate.validator.constraints.Email
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "usuario")
open class Usuario : Versionado() {

    @NotNull
    var nome: String? = null

    @Email
    @NotNull
    var email: String? = null
}