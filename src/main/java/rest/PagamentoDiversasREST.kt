package rest

import rest.data.PagamentoDiversaData
import java.util.*
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.Response

@Path("pagamento/diversas")
internal open class PagamentoDiversasREST {

    @GET
    @Produces("application/json")
    open fun pesquisar(): List<PagamentoDiversaData>? {
        return null
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    open fun obter(@PathParam("id") id: UUID): Response {
        return Response.ok().build()
    }
}