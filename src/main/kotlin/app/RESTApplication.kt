package app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RESTApplication

fun main(args: Array<String>) {
    runApplication<RESTApplication>(*args)
}
