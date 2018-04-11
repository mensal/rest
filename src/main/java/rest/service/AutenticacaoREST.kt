package rest.service

import rest.data.AutenticacaoReqData
import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("autenticacao")
open class AutenticacaoREST {

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    open fun autenticar(@Valid data: AutenticacaoReqData) {
        println("${data.login} ${data.senha}")
    }
}