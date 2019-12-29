package rest.service

import core.persistence.UsuarioResumoPagamentoDAO
import rest.UnprocessableEntityException
import rest.data.UsuarioResumoPagamentoResData
import rest.security.Logado
import rest.service.CrudREST.Companion.lancarExcecaoSeNecessario
//import rest.service.CrudREST.Companion.valida
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam

@Path("api/pagamento/resumo")
class PagamentoResumoREST {

    private val violationException = UnprocessableEntityException()

    @GET
    @Logado
    @Produces("application/json")
    fun resumo(@QueryParam("ano") ano: Int?, @QueryParam("mes") mes: Int?): List<UsuarioResumoPagamentoResData>? {
//        valida(ano, mes, violationException)
        lancarExcecaoSeNecessario(violationException)

        var persistidos = UsuarioResumoPagamentoDAO.instance().pesquisar(ano!!, mes!!)

        val resultado = persistidos.map {
            val entidade = it

            val data = UsuarioResumoPagamentoResData()
            data.preencherCom(entidade)
            data
        }

        return if (resultado.isEmpty()) null else resultado
    }
}
