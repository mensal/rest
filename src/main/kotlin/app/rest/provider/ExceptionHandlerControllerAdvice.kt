package app.rest.provider

import app.rest.ClientViolationException
import app.rest.UnprocessableEntityException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException

@ControllerAdvice
class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(value = [ClientViolationException::class])
    fun toResponse(exception: ClientViolationException) = ResponseEntity.status(exception.statusCode).body(exception.violations)


    @ExceptionHandler(value = [BindException::class])
    fun toResponse(exception: BindException): ResponseEntity<List<ClientViolationException.Violation>> {
        val cvException = UnprocessableEntityException()
        exception.fieldErrors.forEach { cvException.addViolation(it.field, it.defaultMessage ?: "") }

        if (cvException.violations.isEmpty()) throw exception else return toResponse(cvException)
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun toResponse(exception: MethodArgumentNotValidException): ResponseEntity<List<ClientViolationException.Violation>> {
        val cvException = UnprocessableEntityException()
        exception.bindingResult.fieldErrors.forEach { cvException.addViolation(it.field, it.defaultMessage ?: "") }

        if (cvException.violations.isEmpty()) throw exception else return toResponse(cvException)
    }

    @ExceptionHandler(value = [HttpClientErrorException::class])
    fun toResponse(exception: HttpClientErrorException): ResponseEntity<Any> {
        return ResponseEntity.status(exception.statusCode).build()
    }

    @ExceptionHandler(value = [HttpMessageConversionException::class])
    fun toResponse(exception: HttpMessageConversionException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
    }
}