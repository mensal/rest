package app.rest.provider

import app.rest.ClientViolationException
import app.rest.ClientViolationException.Violation
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.http.MediaType.APPLICATION_JSON as APPLICATION_JSON1

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

@ControllerAdvice
class ClientViolationExceptionHandler {

    @ExceptionHandler(value = [ ClientViolationException::class ])
    fun toResponse(exception: ClientViolationException) : ResponseEntity<List<Violation>> {
        return ResponseEntity.status(exception.statusCode).contentType(APPLICATION_JSON1).body(exception.violations)
    }
}