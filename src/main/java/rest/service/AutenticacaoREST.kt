package rest.service

import rest.data.AutenticacaoReqData
import rest.security.Autenticador
import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("autenticacao")
open class AutenticacaoREST {

    @POST
    @Consumes("application/json")
    @Produces("application/jwt")
    open fun autenticar(@Valid data: AutenticacaoReqData) = Autenticador.instance().autenticar(data.login, data.senha)
}