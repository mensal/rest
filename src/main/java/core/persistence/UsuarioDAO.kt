package core.persistence

import core.entity.Usuario
import javax.enterprise.inject.spi.CDI

open class UsuarioDAO protected constructor() : CrudDAO<Usuario>() {

    override val entityClass = Usuario::class.java

    companion object {
        fun instance() = CDI.current().select(UsuarioDAO::class.java).get()!!
    }
}