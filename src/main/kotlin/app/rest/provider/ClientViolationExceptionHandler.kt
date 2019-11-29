package app.rest.provider

import app.rest.ClientViolationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ClientViolationExceptionHandler {

    @ExceptionHandler(value = [ClientViolationException::class])
    fun toResponse(exception: ClientViolationException) = ResponseEntity.status(exception.statusCode).body(exception.violations)
}