//package app.rest.security
//
//
//import app.rest.UnauthorizedException
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Component
//import javax.enterprise.inject.spi.CDI
//import javax.interceptor.AroundInvoke
//import javax.interceptor.Interceptor
//import javax.interceptor.InvocationContext
//import javax.servlet.http.HttpServletRequest
//
////@Component
//@Logado
//@Interceptor
//class LogadoInterceptor protected constructor() {
//
//    @Autowired
//    lateinit var autenticador: Autenticador
//
//    @AroundInvoke
//    @Throws(Exception::class)
//    fun manage(ic: InvocationContext): Any? {
//        val header = header() ?: throw UnauthorizedException()
//        val token = token(header) ?: throw UnauthorizedException()
//
//        try {
//            autenticador.autenticar(token)
//        } catch (e: Throwable) {
//            throw UnauthorizedException(e)
//        }
//
//        return ic.proceed()
//    }
//
//    private fun header() = CDI.current().select(HttpServletRequest::class.java).get().getHeader("Authorization")
//
//    private fun token(header: String) = " *(Bearer) +([A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_.+/=]+ *)".toRegex(RegexOption.IGNORE_CASE).matchEntire(header)?.groupValues?.get(2)
//}
