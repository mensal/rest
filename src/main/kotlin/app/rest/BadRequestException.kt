package app.rest

import org.springframework.context.annotation.Scope
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY
import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST

@Component
@Scope(SCOPE_REQUEST)
class BadRequestException : ClientViolationException(BAD_REQUEST)