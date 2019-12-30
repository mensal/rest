package rest.service

import core.persistence.UsuarioDAO
import rest.data.UsuarioResData
import rest.security.Logado
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("usuarios")
class UsuariosREST {

    @Inject
    lateinit var usuarioDAO: UsuarioDAO

    @GET
    @Logado
    @Produces("application/json")
    fun pesquisar(): List<UsuarioResData>? {
        val resultado = usuarioDAO.pesquisar().map {
            val data = UsuarioResData()
            data.preencherCom(it)
            data
        }

        return if (resultado.isEmpty()) null else resultado
    }
}