package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.Usuario
import java.util.*

@JsonPropertyOrder("id", "nome")
class UsuarioResData : ResData<Usuario> {

    var id: UUID? = null

    var nome: String? = null

    override fun preencherCom(entidade: Usuario?) {
        id = entidade?.id
        nome = entidade?.nome
    }
}