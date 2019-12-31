package rest.service

import core.persistence.UsuarioDAO
import rest.data.UsuarioResData
import rest.security.Logado
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.UriInfo

@Path("usuarios")
class UsuariosREST {

    @Inject
    lateinit var usuarioDAO: UsuarioDAO

    @GET
    @Logado
    @Produces("application/json")
    fun pesquisar(@Context uriInfo: UriInfo): List<UsuarioResData>? {
        val params = mutableMapOf<String, String>()
        uriInfo.queryParameters.forEach { params[it.key] = it.value.first() }

        val resultado = usuarioDAO.pesquisar(params).map {
            val data = UsuarioResData()
            data.preencherCom(it)
            data
        }

        return if (resultado.isEmpty()) null else resultado
    }
}