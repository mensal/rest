package rest.service

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("api/probe")
class ProbeREST {

    @GET
    @Path("readiness")
    fun readiness() = null

    @GET
    @Path("liveness")
    fun liveness() = null
}
