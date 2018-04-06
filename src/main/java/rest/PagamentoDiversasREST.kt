package rest

import rest.data.PagamentoDiversaData
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.*

@Path("pagamento/diversas")
internal open class PagamentoDiversasREST {

    @GET
    @Produces("application/json")
    open fun pesquisar(): List<PagamentoDiversaData>? {
        return null
    }

    @POST
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun inserir(@Valid data: PagamentoDiversaData, @Context uriInfo: UriInfo): Response {
        return Response.ok().build()
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    open fun obter(@PathParam("id") id: UUID): Response {
        return Response.ok().build()
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun atualizar(@PathParam("id") id: UUID, @Valid data: PagamentoDiversaData, @Context headers: HttpHeaders, @Context request: Request): Response {
        return Response.ok().build()
    }
}