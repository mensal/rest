package app.rest.provider

import app.rest.ClientViolationException
import app.rest.UnprocessableEntityException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*
import java.util.Arrays.asList
//import javax.inject.Inject
import javax.validation.ConstraintViolationException
import javax.validation.ElementKind.PROPERTY
import javax.validation.ElementKind.PARAMETER
//import javax.ws.rs.core.Response
//import javax.ws.rs.ext.ExceptionMapper
//import javax.ws.rs.ext.Provider

//@Provider
//@Component
@ControllerAdvice
class ConstraintViolationExceptionHandler {
//class ConstraintViolationExceptionMapper : ExceptionMapper<ConstraintViolationException> {

    @Autowired
    private lateinit var mapper: ClientViolationExceptionHandler

    @ExceptionHandler(value = [ BindException::class ])
    fun toResponse(exception: BindException): ResponseEntity<List<ClientViolationException.Violation>> {
//        override fun toResponse(exception: ConstraintViolationException): Response {
        val cvException = UnprocessableEntityException()

        exception.fieldErrors.forEach { cvException.addViolation(it.field, it.defaultMessage ?: "" ) }

//        for (violation in exception.constraintViolations) {
//            val properties = violation.propertyPath.filter { it.kind == PROPERTY }.map { it.name }
//            val parameters = violation.propertyPath.filter { it.kind == PARAMETER }.map { it.name }
//            val path = if (properties?.size > 0) properties else parameters
//
//            if (path?.size > 0) cvException.addViolation(path.joinToString("."), violation.message)
//        }

//        if (cvException.violations.isEmpty()) throw exception else return mapper.toResponse(cvException)
        if (cvException.violations.isEmpty()) throw exception else return mapper.toResponse(cvException)
    }

    @ExceptionHandler(value = [ MethodArgumentNotValidException::class ])
    fun toResponse(exception: MethodArgumentNotValidException): ResponseEntity<List<ClientViolationException.Violation>> {
//        override fun toResponse(exception: ConstraintViolationException): Response {
        val cvException = UnprocessableEntityException()

        exception.bindingResult.fieldErrors.forEach { cvException.addViolation(it.field, it.defaultMessage ?: "" ) }

//        exception.bindingResult.errors

//        for (violation in exception.constraintViolations) {
//            val properties = violation.propertyPath.filter { it.kind == PROPERTY }.map { it.name }
//            val parameters = violation.propertyPath.filter { it.kind == PARAMETER }.map { it.name }
//            val path = if (properties?.size > 0) properties else parameters
//
//            if (path?.size > 0) cvException.addViolation(path.joinToString("."), violation.message)
//        }

//        if (cvException.violations.isEmpty()) throw exception else return mapper.toResponse(cvException)
        if (cvException.violations.isEmpty()) throw exception else return mapper.toResponse(cvException)
    }
}