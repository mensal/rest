package core.entity

import org.hibernate.validator.constraints.Email
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "usuario")
open class Usuario : Versionado() {

    @NotNull
    open var nome: String? = null

    @Email
    @NotNull
    open var email: String? = null
}