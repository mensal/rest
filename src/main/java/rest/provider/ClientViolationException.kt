package br.gov.serpro.ssdk.rest

import javax.ws.rs.ClientErrorException

class ClientViolationException : ClientErrorException {

    private val violations = mutableListOf<Violation>()

    constructor(status: Int) : super(status)

//    constructor(status: Int, cause: Throwable) : super(status, cause) {}

//    constructor(message: String, status: Int, cause: Throwable) : super(message, status, cause) {}

//    fun addViolation(message: String): ClientViolationException {
//        return addViolation(null, message)
//    }

    fun addViolation(property: String? = null, message: String): ClientViolationException {
        this.violations.add(Violation(property, message))
        return this
    }

//    fun getMessage(): String {
//        return if (!violations.isEmpty()) StringUtils.join(violations, ", ") else "Falha na validação dos dados ou erro de negócio."
//    }

    fun getViolations(): List<Violation> {
        return violations
    }

    data class Violation(var property: String?, var message: String) {

//        override fun toString(): String? {
//            return if (StringUtils.isEmpty(this.property)) this.message else "[" + this.property + "] " + this.message
//        }
    }
}