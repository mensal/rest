package app.rest.provider

import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

@ControllerAdvice
class NoContentControllerAdvice : ResponseBodyAdvice<Any> {

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>) =
            returnType.isOptional || returnType.parameterType.isAssignableFrom(Collection::class.java)


    override fun beforeBodyWrite(body: Any?, returnType: MethodParameter, mediaType: MediaType,
                                 converterType: Class<out HttpMessageConverter<*>>, request: ServerHttpRequest, response: ServerHttpResponse): Any? {
        if (body == null || (body as? Collection<Any>)?.isEmpty() == true) response.setStatusCode(HttpStatus.NO_CONTENT)
        return body
    }
}