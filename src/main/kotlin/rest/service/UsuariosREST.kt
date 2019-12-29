package rest.service

import core.persistence.UsuarioDAO
import rest.data.UsuarioResData
import rest.security.Logado
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("usuarios")
class UsuariosREST {

    @GET
    @Logado
    @Produces("application/json")
    fun pesquisar(): List<UsuarioResData>? {
        val resultado = UsuarioDAO.instance().pesquisar(emptyMap()).map {
            val data = UsuarioResData()
            data.preencherCom(it)
            data
        }

        return if (resultado.isEmpty()) null else resultado
    }
}