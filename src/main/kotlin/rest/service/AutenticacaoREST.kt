package rest.service

import org.jboss.resteasy.annotations.GZIP
import rest.data.AutenticacaoReqData
import rest.security.Autenticador
import javax.inject.Inject
import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("autenticacao")
class AutenticacaoREST {

    @Inject
    lateinit var autenticador: Autenticador

    @GZIP
    @POST
    @Consumes("application/json")
    @Produces("application/jwt")
    fun autenticar(@Valid data: AutenticacaoReqData) = autenticador.autenticar(data.login, data.senha)
}