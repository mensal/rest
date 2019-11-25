//package app.rest.provider
//
//import app.rest.ClientViolationException
//import app.rest.UnprocessableEntityException
//import org.springframework.http.HttpStatus
//import org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY
//import org.springframework.http.ResponseEntity
//import org.springframework.stereotype.Component
//import org.springframework.web.bind.annotation.ControllerAdvice
//import org.springframework.web.bind.annotation.ExceptionHandler
//import java.util.*
//import java.util.Arrays.asList
////import javax.inject.Inject
//import javax.validation.ConstraintViolationException
//import javax.validation.ElementKind.PROPERTY
//import javax.validation.ElementKind.PARAMETER
////import javax.ws.rs.core.Response
////import javax.ws.rs.ext.ExceptionMapper
////import javax.ws.rs.ext.Provider
//
////@Provider
////@Component
//@ControllerAdvice
//class ConstraintViolationExceptionMapper {
////class ConstraintViolationExceptionMapper : ExceptionMapper<ConstraintViolationException> {
//
////    @Inject
////    private lateinit var mapper: ClientViolationExceptionMapper
//
//    @ExceptionHandler(value = [ ConstraintViolationException::class ])
//    fun toResponse(exception: ConstraintViolationException): ResponseEntity<ClientViolationException.Violation> {
////        override fun toResponse(exception: ConstraintViolationException): Response {
//        val cvException = UnprocessableEntityException()
//
//        for (violation in exception.constraintViolations) {
//            val properties = violation.propertyPath.filter { it.kind == PROPERTY }.map { it.name }
//            val parameters = violation.propertyPath.filter { it.kind == PARAMETER }.map { it.name }
//            val path = if (properties?.size > 0) properties else parameters
//
//            if (path?.size > 0) cvException.addViolation(path.joinToString("."), violation.message)
//        }
//
////        if (cvException.violations.isEmpty()) throw exception else return mapper.toResponse(cvException)
//        if (cvException.violations.isEmpty()) throw exception else return ResponseEntity(UNPROCESSABLE_ENTITY)
//    }
//}