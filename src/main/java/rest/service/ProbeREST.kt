package rest.service

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("probe")
open class ProbeREST {

    @GET
    @Path("readiness")
    open fun readiness() = null

    @GET
    @Path("liveness")
    open fun liveness() = null
}
