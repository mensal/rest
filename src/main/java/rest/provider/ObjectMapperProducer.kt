package rest.provider

import br.gov.serpro.ssdk.rest.EmptyStringAsNull
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature.*
import com.fasterxml.jackson.databind.module.SimpleModule
import java.util.*
import javax.enterprise.inject.Produces
import javax.enterprise.inject.spi.CDI
import javax.inject.Singleton

internal class ObjectMapperProducer private constructor() {

    @Produces
    @Singleton
    private fun create(): ObjectMapper {
        val module = SimpleModule("Custom Module")
        module.addDeserializer(String::class.java, EmptyStringAsNull())

        val objectMapper = ObjectMapper()
        with(objectMapper) {
            registerModule(module)
            configure(INDENT_OUTPUT, true)
            configure(WRITE_ENUMS_USING_TO_STRING, true)
            configure(WRITE_DATES_AS_TIMESTAMPS, false)
            configure(FAIL_ON_EMPTY_BEANS, false)
            configure(FAIL_ON_UNKNOWN_PROPERTIES, true)
            setSerializationInclusion(NON_NULL)

            dateFormat.timeZone = TimeZone.getDefault()
        }

        return objectMapper
    }

    companion object {

        @JvmStatic
        fun get() = CDI.current().select(ObjectMapper::class.java).get()!!
    }
}