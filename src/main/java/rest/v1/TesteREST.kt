package rest.v1

import java.time.Instant
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("teste")
internal open class TesteREST {

    @GET
    @Produces("text/plain")
    open fun teste() = Instant.now()
}