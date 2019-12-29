package rest.service

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("api/probe")
class ProbeREST {

    @GET
    @Path("readiness")
    open fun readiness() = null

    @GET
    @Path("liveness")
    open fun liveness() = null
}
