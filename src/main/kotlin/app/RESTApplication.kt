package app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
class RESTApplication

fun main(args: Array<String>) {
    runApplication<RESTApplication>(*args)
//    SpringApplication.run(RESTApplication::class.java, *args)
}
