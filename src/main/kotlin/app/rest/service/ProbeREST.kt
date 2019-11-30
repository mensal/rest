package app.rest.service

import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/probe")
class ProbeREST {

//    @ResponseStatus(NO_CONTENT)
//    @ResponseStatus(NO_CONTENT)
    @GetMapping("readiness")
    fun readiness(): Nothing? = null
//    fun readiness() = "opaaaaaaa"

    @GetMapping("liveness")
    fun liveness(): Nothing? = null
}
