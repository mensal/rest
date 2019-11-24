//package app.rest.provider
//
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import javax.enterprise.inject.spi.CDI
//import javax.ws.rs.Consumes
//import javax.ws.rs.Produces
//import javax.ws.rs.ext.ContextResolver
//import javax.ws.rs.ext.Provider
//
//
//@Provider
//@Consumes("application/json")
//@Produces("application/json")
//open class JacksonContextResolver : ContextResolver<ObjectMapper> {
//
//    private var objectMapper: ObjectMapper? = null
//
//    init {
//        objectMapper = CDI.current().select(ObjectMapper::class.java).get()
//    }
//
//    override fun getContext(type: Class<*>): ObjectMapper? {
//        return objectMapper
//    }
//}
