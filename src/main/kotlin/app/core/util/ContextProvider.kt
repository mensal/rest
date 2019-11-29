package app.core.util

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
private class ContextProvider : ApplicationContextAware {

    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }

    companion object {

        private lateinit var context: ApplicationContext

        fun <T : Any> getBean(beanClass: KClass<T>): T {
            return context.getBean(beanClass.java)
        }
    }
}

//companion object {
//    fun <T : Any> Any.Companion.autowired(): T = ContextProvider.getBean(this::class) as T
//}
//fun <T: Any> KClass<T>.autowired(): T = ContextProvider.getBean(this)

fun <T : Any> autowired(beanClass: KClass<T>): T = ContextProvider.getBean(beanClass)
