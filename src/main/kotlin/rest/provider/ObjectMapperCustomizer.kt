package rest.provider

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.quarkus.jackson.ObjectMapperCustomizer
import java.util.*
import javax.inject.Singleton

@Singleton
class ObjectMapperCustomizer : ObjectMapperCustomizer {

    override fun customize(objectMapper: ObjectMapper?) {
        if (objectMapper == null) return

        with(objectMapper) {
            registerModule(JavaTimeModule())

            configure(SerializationFeature.INDENT_OUTPUT, true)
            configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
            configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
            setSerializationInclusion(JsonInclude.Include.NON_NULL)

            dateFormat.timeZone = TimeZone.getDefault()
        }
    }
}