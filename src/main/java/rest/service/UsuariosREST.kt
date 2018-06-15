package rest.service

import core.persistence.UsuarioDAO
import rest.data.UsuarioResData
import rest.security.Logado
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MultivaluedHashMap

@Path("usuarios")
open class UsuariosREST {

    @GET
    @Logado
    @Produces("application/json")
    open fun pesquisar(): List<UsuarioResData>? {
        val params = MultivaluedHashMap<String, String>()

        val resultado = UsuarioDAO.instance().pesquisar(params).map {
            val data = UsuarioResData()
            data.preencherCom(it)
            data
        }

        return if (resultado.isEmpty()) null else resultado
    }
}