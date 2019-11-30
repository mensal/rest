package app.rest.security

import java.lang.annotation.Inherited
//import javax.interceptor.InterceptorBinding
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION

@Inherited
@Retention(RUNTIME)
@Target(FUNCTION, CLASS)
annotation class Logado
