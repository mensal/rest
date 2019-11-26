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

        fun <T: Any> getBean(beanClass: KClass<T>): T {
            return context.getBean(beanClass.java)
        }
    }
}

fun <T: Any> autowired(beanClass: KClass<T>): T {
    return ContextProvider.getBean(beanClass)
}
