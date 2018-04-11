package rest

import javax.ws.rs.ClientErrorException

class UnauthorizedException : ClientErrorException(401)