package rest.v1

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("probe")
internal open class ProbeREST {

    @GET
    @Path("readiness")
    open fun readiness() {
    }

    @GET
    @Path("liveness")
    open fun liveness() {
    }
}
