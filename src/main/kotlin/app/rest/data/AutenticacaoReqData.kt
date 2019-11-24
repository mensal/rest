package app.rest.data

import app.core.entity.Usuario
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class AutenticacaoReqData : ReqData<Usuario> {

    @NotBlank
    lateinit var login: String

    @NotBlank
    lateinit var senha: String

    @Valid
    @NotNull
    lateinit var x: X

    override fun escreverEm(entidade: Usuario) {
        entidade.email = login
    }
}

class X {

    @NotBlank
    lateinit var y: String
}