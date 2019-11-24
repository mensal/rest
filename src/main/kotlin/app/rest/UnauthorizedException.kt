package app.rest

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.web.client.HttpClientErrorException

//import javax.ws.rs.ClientErrorException

//class UnauthorizedException(cause: Throwable? = null) : ClientErrorException(401, cause)
class UnauthorizedException(cause: Throwable? = null) : HttpClientErrorException(UNAUTHORIZED)
