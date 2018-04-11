package rest

import javax.ws.rs.ClientErrorException

class UnauthorizedException(cause: Throwable? = null) : ClientErrorException(401, cause)