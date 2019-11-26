package app.rest

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.client.HttpClientErrorException


class NotFoundException : HttpClientErrorException(NOT_FOUND)