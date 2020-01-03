package rest.service

import core.entity.Versionado
import core.persistence.CrudDAO
import org.apache.commons.lang.time.DateUtils
import org.jboss.resteasy.spi.ResteasyProviderFactory
import rest.ClientViolationException
import rest.PreconditionFailedException
import rest.data.RequestData
import rest.data.ResponseData
import java.util.*
import javax.ws.rs.NotFoundException
import javax.ws.rs.PathParam
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Request
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

interface CrudRESTDelegate<E, in I : RequestData<E>> {

    fun antesDePesquisar(params: MutableMap<String, String>) {}
    fun depoisDePesquisar(entidades: List<E>) {}
    fun depoisDePesquisar(entidade: E) {}

    fun antesDePersistir(entidade: E, requestData: I) {}
    fun depoisDePersistir(entidade: E, requestData: I) {}

    fun antesDeExcluir(entidade: E) {}

    fun add(violation: ClientViolationException.Violation) {}
}

interface CrudREST {
    companion object {
        const val uuidRegex = "\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}"

        private fun <E : Versionado> carregar(id: UUID, dao: CrudDAO<E>) = dao.obter(id) ?: throw NotFoundException()

        fun <E : Versionado, I : RequestData<E>, O : ResponseData<E>>
                pesquisar(resClass: KClass<O>, dao: CrudDAO<E>, delegate: CrudRESTDelegate<E, I>? = null): List<O>? {

            val params = mutableMapOf<String, String>()

            val uriInfo = ResteasyProviderFactory.getInstance().getContextData(UriInfo::class.java)
            uriInfo.queryParameters.forEach { params[it.key] = it.value.first() }

            delegate?.antesDePesquisar(params)

//            valida(params)
//            lancarExcecaoSeNecessario()

            var persistidos = dao.pesquisar(params)
            delegate?.depoisDePesquisar(persistidos)

            val resultado = persistidos.map { p ->
                var entidade = p
                delegate?.depoisDePesquisar(entidade)

                val data = resClass.createInstance()
                data.preencherCom(entidade)
                data
            }

            return if (resultado.isEmpty()) null else resultado
        }

        fun <E : Versionado, I : RequestData<E>, O : ResponseData<E>>
                obter(@PathParam("id") id: UUID, resClass: KClass<O>, dao: CrudDAO<E>, delegate: CrudRESTDelegate<E, I>? = null): Response {

            var persistido = carregar(id, dao)
            delegate?.depoisDePesquisar(persistido)

            val resultado = resClass.createInstance()
            resultado.preencherCom(persistido)

            val atualizadoEm = Date.from(persistido.atualizadoEm!!.toInstant())
            return Response.ok().entity(resultado).lastModified(atualizadoEm).build()
        }

        fun <E : Versionado, I : RequestData<E>, O : ResponseData<E>>
                inserir(data: I, type: KClass<E>, resClass: KClass<O>, dao: CrudDAO<E>, delegate: CrudRESTDelegate<E, I>? = null): Response {

            val entidade = type.createInstance()
            data.escreverEm(entidade)

            delegate?.antesDePersistir(entidade, data)
            // TODO: lança exceção se necessário

            dao.inserir(entidade)

            delegate?.depoisDePersistir(entidade, data)
            // TODO: lança exceção se necessário

            val uriInfo = ResteasyProviderFactory.getInstance().getContextData(UriInfo::class.java)
            val location = uriInfo.requestUriBuilder.path("${entidade.id}").build()
            val responseData = resClass.createInstance()
            responseData.preencherCom(entidade)

            val atualizadoEm = Date.from(entidade.atualizadoEm!!.toInstant())
            return Response.created(location).entity(responseData).lastModified(atualizadoEm).build()
        }

        fun <E : Versionado, I : RequestData<E>, O : ResponseData<E>>
                atualizar(id: UUID, data: I, resClass: KClass<O>, dao: CrudDAO<E>, delegate: CrudRESTDelegate<E, I>? = null): Response {

            var persistido = carregar(id, dao)
            var builder = buildSeModificado(persistido)

            if (builder == null) {
                data.escreverEm(persistido)

                delegate?.antesDePersistir(persistido, data)
                persistido = dao.atualizar(persistido)
                delegate?.depoisDePersistir(persistido, data)
//                lancarExcecaoSeNecessario()

                val responseData = resClass.createInstance()
                responseData.preencherCom(persistido)

                val atualizadoEm = Date.from(persistido.atualizadoEm!!.toInstant())
                builder = Response.ok().entity(responseData).lastModified(atualizadoEm)
            }

            return builder!!.build()
        }

        fun <E : Versionado, I : RequestData<E>, O : ResponseData<E>>
                deletar(@PathParam("id") id: UUID, resClass: KClass<O>, dao: CrudDAO<E>, delegate: CrudRESTDelegate<E, I>? = null): O {

            val persistido = carregar(id, dao)

            delegate?.antesDeExcluir(persistido)
            dao.excluir(persistido)
//            lancarExcecaoSeNecessario()

            val responseData = resClass.createInstance()
            responseData.preencherCom(persistido)

            return responseData
        }

//        fun valida(ano: Int?, mes: Int?, exception: ClientViolationException) {
//            if (ano == null) exception.addViolation("ano", "parâmetro obrigatório")
//            if (mes == null) exception.addViolation("mes", "parâmetro obrigatório")
//        }

        private fun buildSeModificado(versionado: Versionado): Response.ResponseBuilder? {
            var request = ResteasyProviderFactory.getInstance().getContextData(Request::class.java)
            var headers = ResteasyProviderFactory.getInstance().getContextData(HttpHeaders::class.java)

            headers.getHeaderString("If-Unmodified-Since") ?: throw PreconditionFailedException()

            return try {
                val atualizadoEm = Date.from(versionado.atualizadoEm!!.toInstant())
                request.evaluatePreconditions(DateUtils.truncate(atualizadoEm, Calendar.SECOND))

            } catch (cause: Exception) {
//                cause.printStackTrace()
                throw PreconditionFailedException()
            }
        }

        fun lancarExcecaoSeNecessario(exception: ClientViolationException) {
            if (exception.violations.isNotEmpty()) throw exception
        }
    }
}