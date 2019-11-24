//package app.rest.service
//
//import app.core.persistence.UsuarioResumoPagamentoDAO
//import app.rest.UnprocessableEntityException
//import app.rest.data.UsuarioResumoPagamentoResData
//import app.rest.security.Logado
//import app.rest.service.CrudREST.Companion.lancarExcecaoSeNecessario
////import app.rest.service.CrudREST.Companion.valida
//import javax.ws.rs.GET
//import javax.ws.rs.Path
//import javax.ws.rs.Produces
//import javax.ws.rs.QueryParam
//
//@Path("pagamento/resumo")
//open class PagamentoResumoREST {
//
//    private val violationException = UnprocessableEntityException()
//
//    @GET
//    @Logado
//    @Produces("application/json")
//    open fun resumo(@QueryParam("ano") ano: Int?, @QueryParam("mes") mes: Int?): List<UsuarioResumoPagamentoResData>? {
////        valida(ano, mes, violationException)
//        lancarExcecaoSeNecessario(violationException)
//
//        var persistidos = UsuarioResumoPagamentoDAO.instance().pesquisar(ano!!, mes!!)
//
//        val resultado = persistidos.map {
//            val entidade = it
//
//            val data = UsuarioResumoPagamentoResData()
//            data.preencherCom(entidade)
//            data
//        }
//
//        return if (resultado.isEmpty()) null else resultado
//    }
//}
