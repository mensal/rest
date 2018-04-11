package rest

import javax.ws.rs.ClientErrorException

class PreconditionFailedException : ClientErrorException(412)