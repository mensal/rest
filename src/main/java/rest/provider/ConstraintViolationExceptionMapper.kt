package br.gov.serpro.ssdk.rest.mapper

import br.gov.serpro.ssdk.rest.ClientViolationException
import javax.inject.Inject
import javax.validation.ConstraintViolationException
import javax.validation.ElementKind.PROPERTY
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
open class ConstraintViolationExceptionMapper : ExceptionMapper<ConstraintViolationException> {

    @Inject
    private lateinit var mapper: ClientViolationExceptionMapper

    override fun toResponse(exception: ConstraintViolationException): Response {
        val cvException = ClientViolationException(422)

        for (violation in exception.constraintViolations) {
            val path = mutableListOf<String>()
            violation.propertyPath.iterator().forEach { if (it.kind == PROPERTY) path.add(it.name) }

            if (path.size > 0) cvException.addViolation(path.joinToString("."), violation.message)
        }

        if (cvException.getViolations().isEmpty()) throw exception else return mapper.toResponse(cvException)
    }
}