package app.rest.provider

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException

@ControllerAdvice
class HttpClientErrorExceptionHandler {

    @ExceptionHandler(value = [HttpClientErrorException::class])
    fun toResponse(exception: HttpClientErrorException): ResponseEntity<Any> {

        return ResponseEntity.status(exception.statusCode).build()
    }
}