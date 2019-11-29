package app.rest

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException

open class ClientViolationException(status: HttpStatus) : HttpClientErrorException(status) {

    val violations: List<Violation>
        get() = _violations.toList()

    private val _violations = mutableListOf<Violation>()

    fun addViolation(message: String): ClientViolationException = addViolation(null, message)

    fun addViolation(property: String?, message: String): ClientViolationException {
        this._violations.add(Violation(property, message))
        return this
    }

    data class Violation(var property: String?, var message: String)
}