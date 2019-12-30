package rest.provider

import rest.UnprocessableEntityException
import javax.inject.Inject
import javax.validation.ConstraintViolationException
import javax.validation.ElementKind.PROPERTY
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
open class ConstraintViolationExceptionMapper : ExceptionMapper<ConstraintViolationException> {

    @Inject
    lateinit var mapper: ClientViolationExceptionMapper
//    private var mapper = ClientViolationExceptionMapper()

    override fun toResponse(exception: ConstraintViolationException): Response {
        val cvException = UnprocessableEntityException()

        for (violation in exception.constraintViolations) {
            val path = mutableListOf<String>()
            violation.propertyPath.iterator().forEach { if (it.kind == PROPERTY) path.add(it.name) }

            if (path.size > 0) cvException.addViolation(path.joinToString("."), violation.message)
        }

        if (cvException.violations.isEmpty()) throw exception else return mapper.toResponse(cvException)
    }
}