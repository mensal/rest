//package app.rest.service
//
//import app.core.persistence.UsuarioDAO
//import app.rest.data.UsuarioResData
//import app.rest.security.Logado
//import javax.ws.rs.GET
//import javax.ws.rs.Path
//import javax.ws.rs.Produces
//
//@Path("usuarios")
//open class UsuariosREST {
//
//    @GET
//    @Logado
//    @Produces("application/json")
//    open fun pesquisar(): List<UsuarioResData>? {
//        val resultado = UsuarioDAO.instance().pesquisar(emptyMap()).map {
//            val data = UsuarioResData()
//            data.preencherCom(it)
//            data
//        }
//
//        return if (resultado.isEmpty()) null else resultado
//    }
//}