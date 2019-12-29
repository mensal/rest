package rest.data

import core.entity.Usuario
import javax.validation.constraints.NotBlank

class AutenticacaoReqData : ReqData<Usuario> {

    @NotBlank
    lateinit var login: String

    @NotBlank
    lateinit var senha: String

    override fun escreverEm(entidade: Usuario) {
        entidade.email = login
    }
}