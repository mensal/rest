package app.rest

import org.springframework.http.HttpStatus.PRECONDITION_FAILED
import org.springframework.web.client.HttpClientErrorException


class PreconditionFailedException : HttpClientErrorException(PRECONDITION_FAILED)