package rest.provider

import rest.ClientViolationException
import javax.inject.Singleton
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
@Singleton
open class ClientViolationExceptionMapper : ExceptionMapper<ClientViolationException> {

    override fun toResponse(exception: ClientViolationException): Response {
        val statusCode = exception.response.statusInfo.statusCode
        return Response.status(statusCode).entity(exception.violations).type("application/json").build()
    }
}