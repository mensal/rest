package rest.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.Usuario
import java.time.ZonedDateTime
import java.util.*

@JsonPropertyOrder("id", "nome", "atualizado_em")
class UsuarioResData : ResData<Usuario> {

    var id: UUID? = null

    var nome: String? = null

    @JsonProperty("atualizado_em")
    var atualizadoEm: ZonedDateTime? = null

    override fun preencherCom(entidade: Usuario?) {
        id = entidade?.id
        nome = entidade?.nome
//        atualizadoEm = entidade?.atualizadoEm
    }
}