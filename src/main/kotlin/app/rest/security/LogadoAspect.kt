package app.rest.security


//import javax.enterprise.inject.spi.CDI
//import javax.interceptor.AroundInvoke
//import javax.interceptor.Interceptor
//import javax.interceptor.InvocationContext
import app.rest.UnauthorizedException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import kotlin.text.RegexOption.IGNORE_CASE

@Aspect
@Component
class LogadoAspect protected constructor() {

    @Autowired
    lateinit var autenticador: Autenticador

    @Autowired
    lateinit var request: HttpServletRequest

    @Throws(Exception::class)
    @Around("@annotation(Logado)")
    fun checkLoggedIn(joinPoint: ProceedingJoinPoint): Any? {
        val header = request.getHeader("Authorization") ?: throw UnauthorizedException()
        val token = token(header) ?: throw UnauthorizedException()

        try {
            autenticador.autenticar(token)
        } catch (e: Throwable) {
            throw UnauthorizedException(e)
        }

        return joinPoint.proceed()
    }

    private fun token(header: String) = BEARER_REGEXP.toRegex(IGNORE_CASE).matchEntire(header)?.groupValues?.get(2)
}

private const val BEARER_REGEXP = " *(Bearer) +([A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_.+/=]+ *)"