package app.rest.service

import app.core.util.autowired
import app.rest.data.AutenticacaoReqData
import app.rest.security.Autenticador
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/autenticacao")
class AutenticacaoREST {

//    @Autowired
//    lateinit var autenticador:

    @PostMapping(produces = ["application/jwt"])
    fun autenticar(@RequestBody @Valid data: AutenticacaoReqData) = autowired(Autenticador::class).autenticar(data.login, data.senha)
}