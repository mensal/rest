package rest.security

import java.lang.annotation.Inherited
import javax.interceptor.InterceptorBinding
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION

@Inherited
@InterceptorBinding
@Retention(RUNTIME)
@Target(FUNCTION, CLASS)
annotation class LoggedIn
