package app.rest.service

import app.core.persistence.UsuarioDAO
import app.core.util.autowired
import app.rest.data.UsuarioResData
import app.rest.security.Logado
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/usuarios")
class UsuariosREST {

    @Logado
    @GetMapping
    fun pesquisar(): List<UsuarioResData>? {
        return autowired(UsuarioDAO::class).pesquisar(emptyMap()).map {
            val data = UsuarioResData()
            data.preencherCom(it)
            data
        }
    }
}