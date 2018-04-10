package rest

import javax.ws.rs.ClientErrorException

open class ClientViolationException(status: Int) : ClientErrorException(status) {

    val violations = mutableListOf<Violation>()

    fun addViolation(message: String): ClientViolationException = addViolation(null, message)

    fun addViolation(property: String?, message: String): ClientViolationException {
        this.violations.add(Violation(property, message))
        return this
    }

    data class Violation(var property: String?, var message: String)
}