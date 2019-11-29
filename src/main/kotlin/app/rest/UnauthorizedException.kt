package app.rest

import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.web.client.HttpClientErrorException


class UnauthorizedException(cause: Throwable? = null) : HttpClientErrorException(UNAUTHORIZED)
