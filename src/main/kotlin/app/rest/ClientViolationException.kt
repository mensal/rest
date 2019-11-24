package app.rest

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException

//import javax.ws.rs.ClientErrorException

open class ClientViolationException(status: HttpStatus) : HttpClientErrorException(status) {
//    open class ClientViolationException(status: Int) : Exception() {

    val violations = mutableListOf<Violation>()

    fun addViolation(message: String): ClientViolationException = addViolation(null, message)

    fun addViolation(property: String?, message: String): ClientViolationException {
        this.violations.add(Violation(property, message))
        return this
    }

    data class Violation(var property: String?, var message: String)
}