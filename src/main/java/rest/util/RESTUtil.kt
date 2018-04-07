package rest.util

import core.entity.Versionado
import org.apache.commons.lang3.time.DateUtils.truncate
import java.util.*
import java.util.Calendar.SECOND
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Request
import javax.ws.rs.core.Response.ResponseBuilder

class RESTUtil private constructor() {

    companion object {
        fun buildIfModified(request: Request, headers: HttpHeaders, versionado: Versionado): ResponseBuilder? {
            headers.getHeaderString("If-Unmodified-Since") ?: throw PreconditionFailedException()

            return try {
                val atualizadoEm = Date.from(versionado.atualizadoEm!!.toInstant())
                request.evaluatePreconditions(truncate(atualizadoEm, SECOND))
            } catch (cause: Exception) {
                cause.printStackTrace()
                throw PreconditionFailedException()
            }
        }
    }
}