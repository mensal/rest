//package br.gov.serpro.ssdk.rest
//
//import com.fasterxml.jackson.core.JsonParser
//import com.fasterxml.jackson.databind.DeserializationContext
//import com.fasterxml.jackson.databind.JsonDeserializer
//import org.apache.commons.lang.StringUtils
//import java.io.IOException
//
//class EmptyStringAsNull : JsonDeserializer<String>() {
//
//    @Throws(IOException::class)
//    override fun deserialize(jp: JsonParser, ctx: DeserializationContext): String? {
//        val string = jp.text
//        return if (StringUtils.isEmpty(string)) null else string
//    }
//}
