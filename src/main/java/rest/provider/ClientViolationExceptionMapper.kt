package br.gov.serpro.ssdk.rest.mapper

import br.gov.serpro.ssdk.rest.ClientViolationException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
open class ClientViolationExceptionMapper : ExceptionMapper<ClientViolationException> {

    override fun toResponse(exception: ClientViolationException): Response {
        val statusCode = exception.response.statusInfo.statusCode
        return Response.status(statusCode).entity(exception.getViolations()).type("application/json").build()
    }
}