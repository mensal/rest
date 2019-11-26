package app.rest

import org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

class UnprocessableEntityException : ClientViolationException(UNPROCESSABLE_ENTITY)