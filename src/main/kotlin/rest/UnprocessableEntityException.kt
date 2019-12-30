package rest

import javax.enterprise.context.RequestScoped

@RequestScoped
open class UnprocessableEntityException : ClientViolationException(422)