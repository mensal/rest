package rest.service

import core.persistence.UsuarioDAO
import rest.data.UsuarioResData
import rest.security.Logado
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam

@Path("usuarios")
open class UsuariosREST {

    @GET
    @Logado
    @Produces("application/json")
    open fun pesquisar(@QueryParam("ano") ano: Int, @QueryParam("mes") mes: Int): List<UsuarioResData>? {
        val resultado = UsuarioDAO.instance().pesquisar(ano, mes).map {
            val data = UsuarioResData()
            data.preencherCom(it)
            data
        }

        return if (resultado.isEmpty()) null else resultado
    }
}