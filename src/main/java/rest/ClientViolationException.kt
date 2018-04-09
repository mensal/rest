package br.gov.serpro.ssdk.rest

import javax.ws.rs.ClientErrorException

open class ClientViolationException : ClientErrorException {

    val violations = mutableListOf<Violation>()

    constructor(status: Int) : super(status)

    fun addViolation(property: String? = null, message: String): ClientViolationException {
        this.violations.add(Violation(property, message))
        return this
    }

    data class Violation(var property: String?, var message: String)
}