package app.rest.provider

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class HttpMessageConversionExceptionHandler {

    @ExceptionHandler(value = [HttpMessageConversionException::class])
    fun toResponse(exception: HttpMessageConversionException): ResponseEntity<Any> {

//        print(exception.sta /)

        return ResponseEntity.status(BAD_REQUEST).build()
    }
}