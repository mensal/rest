package app.rest.service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/probe")
class ProbeREST {

    @GetMapping("readiness")
    fun readiness(): Nothing? = null

    @GetMapping("liveness")
    fun liveness(): Nothing? = null
}
