package rest.v1

import java.time.Instant
import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("teste")
class TesteREST {

    @GET
    fun teste() = Instant.now()
}