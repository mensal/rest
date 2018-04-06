package rest.util

import core.entity.Versionado
import org.apache.commons.lang3.time.DateUtils
import java.util.*
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Request
import javax.ws.rs.core.Response

class RESTUtil private constructor() {

    companion object {
        fun buildIfModified(request: Request, headers: HttpHeaders, versionado: Versionado): Response.ResponseBuilder {
            headers.getHeaderString("If-Unmodified-Since") ?: throw PreconditionFailedException()
            return request.evaluatePreconditions(DateUtils.truncate(versionado.atualizadoEm, Calendar.SECOND))
        }
    }
}