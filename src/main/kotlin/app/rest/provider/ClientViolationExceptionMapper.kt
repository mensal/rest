//package app.rest.provider
//
//import app.rest.ClientViolationException
//import org.springframework.stereotype.Component
//import javax.ws.rs.core.Response
//import javax.ws.rs.ext.ExceptionMapper
//import javax.ws.rs.ext.Provider
//
//@Provider
//@Component
//class ClientViolationExceptionMapper : ExceptionMapper<ClientViolationException> {
//
//    override fun toResponse(exception: ClientViolationException): Response {
//        val statusCode = exception.response.statusInfo.statusCode
//        return Response.status(statusCode).entity(exception.violations).type("application/json").build()
//    }
//}