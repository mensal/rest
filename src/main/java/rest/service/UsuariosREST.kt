package rest.service

import core.persistence.UsuarioDAO
import rest.data.UsuarioResData
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("usuarios")
open class UsuariosREST {

    @GET
    @Produces("application/json")
    open fun pesquisar(): List<UsuarioResData>? {
        val resultado = UsuarioDAO.instance().pesquisar().map {
            val data = UsuarioResData()
            data.preencherCom(it)
            data
        }

        return if (resultado.isEmpty()) null else resultado
    }
}