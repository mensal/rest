package rest.data

import core.entity.Usuario
import org.hibernate.validator.constraints.NotBlank

class AutenticacaoReqData : ReqData<Usuario> {

    @NotBlank
    lateinit var login: String

    @NotBlank
    lateinit var senha: String

    override fun escreverEm(entidade: Usuario) {
        entidade.email = login
    }
}