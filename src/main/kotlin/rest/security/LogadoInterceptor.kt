package rest.security


import rest.UnauthorizedException
import javax.annotation.Priority
import javax.enterprise.inject.spi.CDI
import javax.interceptor.AroundInvoke
import javax.interceptor.Interceptor
import javax.interceptor.InvocationContext
import javax.servlet.http.HttpServletRequest

@Logado
@Priority(0)
@Interceptor
class LogadoInterceptor {

    @AroundInvoke
    @Throws(Exception::class)
    fun manage(ic: InvocationContext): Any? {
        val header = header() ?: throw UnauthorizedException()
        val token = token(header) ?: throw UnauthorizedException()

        try {
            Autenticador.instance().autenticar(token)
        } catch (e: Throwable) {
            throw UnauthorizedException(e)
        }

        return ic.proceed()
    }

    private fun header() = CDI.current().select(HttpServletRequest::class.java).get().getHeader("Authorization")

    private fun token(header: String) = " *(Bearer) +([A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_.+/=]+ *)".toRegex(RegexOption.IGNORE_CASE).matchEntire(header)?.groupValues?.get(2)
}
