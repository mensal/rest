package app.rest.service

import app.rest.data.AutenticacaoReqData
import app.rest.security.Autenticador
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
//import javax.inject.Inject
//import org.springframework.web.bind.annotation.*
//import app.rest.data.AutenticacaoReqData
//import app.rest.security.Autenticador
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
//import javax.ws.rs.Consumes
//import javax.ws.rs.POST
//import javax.ws.rs.Path
//import javax.ws.rs.Produces

//import javax.ws.rs.Path

//import javax.ws.rs.Consumes
//import javax.ws.rs.POST
//import javax.ws.rs.Path
//import javax.ws.rs.Produces

//@Component
//@Path("api/autenticacao")

@RestController
//@Controller
@RequestMapping("api/autenticacao")
class AutenticacaoREST {

    @Autowired
//    @Inject
    lateinit var autenticador: Autenticador

//    @POST
//    @PostMapping
//    @Consumes("application/json")
//    @Produces("application/jwt")
//    fun autenticar(@RequestBody data: AutenticacaoReqData) {
//        print("xxxxxxxxxxxxxxxx")
//    }

//    @GetMapping
//    @ResponseBody
//    fun x() = "s"

//    @POST
    @PostMapping
//    @Consumes("application/json")
//    @Produces("application/jwt")
//    @PostMapping
//    @RequestMapping(produces = ["application/jwt"])
    fun autenticar(@RequestBody @Valid data: AutenticacaoReqData) = autenticador.autenticar(data.login, data.senha)

//    @POST
//    @Path("x")
//    @Post/**/Mapping
//    @Consumes("application/json")
//    @Produces("application/jwt")
//    @PostMapping
//    @RequestMapping(produces = ["application/jwt"])
    fun autenticar2(@NotEmpty data: String) = print(":$data")

}