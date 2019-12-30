package rest.security


import rest.UnauthorizedException
import javax.annotation.Priority
import javax.inject.Inject
import javax.interceptor.AroundInvoke
import javax.interceptor.Interceptor
import javax.interceptor.InvocationContext
import javax.servlet.http.HttpServletRequest
import kotlin.text.RegexOption.IGNORE_CASE

private const val BEARER_REGEXP = " *(Bearer) +([A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_.+/=]+ *)"

@Logado
//@Singleton
//@ApplicationScoped
@Priority(0)
@Interceptor
open class LogadoInterceptor {

    @Inject
    lateinit var autenticador: Autenticador

    @Inject
    lateinit var request: HttpServletRequest

    @AroundInvoke
    @Throws(Exception::class)
    open fun manage(ic: InvocationContext): Any? {
        val header = request.getHeader("Authorization") ?: throw UnauthorizedException()
//        val header = header() ?: throw UnauthorizedException()
        val token = token(header) ?: throw UnauthorizedException()

        try {
            autenticador.autenticar(token)
        } catch (e: Throwable) {
            throw UnauthorizedException(e)
        }

        return ic.proceed()
    }

//    private fun header() = CDI.current().select(HttpServletRequest::class.java).get().getHeader("Authorization")

    //    private fun token(header: String) = " *(Bearer) +([A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_.+/=]+ *)".toRegex(RegexOption.IGNORE_CASE).matchEntire(header)?.groupValues?.get(2)
    private fun token(header: String) = BEARER_REGEXP.toRegex(IGNORE_CASE).matchEntire(header)?.groupValues?.get(2)

}
