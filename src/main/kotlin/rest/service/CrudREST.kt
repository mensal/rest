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

    fun antesDePersistir(entidade: E, requestData: I) {}
    fun depoisDePersistir(entidade: E, requestData: I) {}
    fun antesDeExcluir(entidade: E) {}
    fun depoisDePesquisar(entidade: E) = entidade
    fun depoisDePesquisar(entidades: List<E>) = entidades
    fun add(violation: ClientViolationException.Violation) {}
}

interface CrudREST /* <E : Versionado, I : Requested<E>, O : Responsed<E>, D : VersionadoCrudDAO<E>> */ {

//    fun pesquisar(): List<O>?
//    fun obter(id: UUID): Response
//    fun inserir(@Valid data: I): Response
//    fun atualizar(id: UUID, @Valid data: I): Response
//    fun deletar(id: UUID): O

//    @Inject
//    open lateinit var violationException: UnprocessableEntityException
//
//    protected open val entityClass: KClass<E>
//        get() = Reflections.argument(this, CrudREST::class, 0)
//
//    protected open val resClass: KClass<ResData<E>>
//        get() = Reflections.argument(this, CrudREST::class, 2)
//
//    protected open val daoClass: KClass<CrudDAO<E>>
//        get() = Reflections.argument(this, CrudREST::class, 3)
//
//    protected open val dao: CrudDAO<E>
//        get() = CDI.current().select(daoClass.java).get()!!
//
//    protected open fun novaEntidade() = entityClass.createInstance()
//
//    protected open fun novoResponseData() = resClass.createInstance()
//
//    protected open fun antesDePersistir(entidade: E, requestData: ReqData<E>) {}
//
//    protected open fun depoisDePersistir(entidade: E, requestData: ReqData<E>) {}
//
//    protected open fun antesDeExcluir(entidade: E) {}
//
//    protected open fun depoisDePesquisar(entidade: E) = entidade
//
//    protected open fun depoisDePesquisar(entidades: List<E>) = entidades
//
//    @GET
//    @GZIP
//    @Logado
//    @Produces("application/json")
//    open fun pesquisar(@Context uriInfo: UriInfo): List<ResData<E>>? {
//        val params = mutableMapOf<String, String>()
//        uriInfo.queryParameters.forEach { params[it.key] = it.value.first() }
//
//        valida(params)
//        lancarExcecaoSeNecessario()
//
//        var persistidos = dao.pesquisar(params)
//        persistidos = depoisDePesquisar(persistidos)
//
//        val resultado = persistidos.map {
//            val entidade = depoisDePesquisar(it)
//
//            val data = novoResponseData()
//            data.preencherCom(entidade)
//            data
//        }
//
//        return if (resultado.isEmpty()) null else resultado
//    }
//
//    @GET
//    @Logado
//    @Path("{id: $uuidRegex}")
//    @Produces("application/json")
//    open fun obter(@PathParam("id") id: UUID): Response {
//        var persistido = carregar(id)
//        persistido = depoisDePesquisar(persistido)
//
//        val resultado = novoResponseData()
//        resultado.preencherCom(persistido)
//
//        val atualizadoEm = Date.from(persistido.atualizadoEm!!.toInstant())
//        return Response.ok().entity(resultado).lastModified(atualizadoEm).build()
//    }
//
//    open fun inserir(@Valid data: ReqData<E>): Response {
//        return inserir(data, entityClass, resClass, dao(daoClass), { e, d ->
//            antesDePersistir(e, d)
//            lancarExcecaoSeNecessario()
//        }, { e, d ->
//            depoisDePersistir(e, d)
//            lancarExcecaoSeNecessario()
//        })
//    }
//
//    @PUT
//    @Logado
//    @Path("{id: $uuidRegex}")
//    @Consumes("application/json")
//    @Produces("application/json")
//    @Transactional(rollbackOn = [Throwable::class])
//    open fun atualizar(@PathParam("id") id: UUID, @Valid data: ReqData<E>): Response {
//        var persistido = carregar(id)
//        var builder = buildSeModificado(persistido)
//
//        if (builder == null) {
//            data.escreverEm(persistido)
//
//            antesDePersistir(persistido, data)
//            persistido = dao.atualizar(persistido)
//            depoisDePersistir(persistido, data)
//            lancarExcecaoSeNecessario()
//
//            val responseData = novoResponseData()
//            responseData.preencherCom(persistido)
//
//            val atualizadoEm = Date.from(persistido.atualizadoEm!!.toInstant())
//            builder = Response.ok().entity(responseData).lastModified(atualizadoEm)
//        }
//
//        return builder!!.build()
//    }
//
//    @DELETE
//    @Logado
//    @Path("{id: $uuidRegex}")
//    @Produces("application/json")
//    @Transactional(rollbackOn = [Throwable::class])
//    open fun deletar(@PathParam("id") id: UUID): ResData<E> {
//        val persistido = carregar(id)
//
//        antesDeExcluir(persistido)
//        dao.excluir(persistido)
//        lancarExcecaoSeNecessario()
//
//        val responseData = novoResponseData()
//        responseData.preencherCom(persistido)
//
//        return responseData
//    }
//
//    private fun carregar(id: UUID) = dao.obter(id) ?: throw NotFoundException()
//
//    protected open fun valida(params: Map<String, String>) {
////        Companion.valida(params, violationException)
//    }
//
//    protected open fun lancarExcecaoSeNecessario() {
//        lancarExcecaoSeNecessario(violationException)
//    }

    companion object {
        const val uuidRegex = "\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}"

//        private fun <E : Versionado> novaEntidade(type: KClass<E>) = type.createInstance()
//
//        private fun <E : Versionado, O : Responsed<E>> novoResponseData(type: KClass<O>) = type.createInstance()

        private fun <E : Versionado> carregar(id: UUID, dao: CrudDAO<E>) = dao.obter(id) ?: throw NotFoundException()

        fun <E : Versionado, O : ResponseData<E>> pesquisar(resClass: KClass<O>,
                                                            dao: CrudDAO<E>,
                                                            delegate: CrudRESTDelegate<E, RequestData<E>>? = null): List<O>? {
            val params = mutableMapOf<String, String>()

            val uriInfo = ResteasyProviderFactory.getInstance().getContextData(UriInfo::class.java)
            uriInfo.queryParameters.forEach { params[it.key] = it.value.first() }

//            valida(params)
//            lancarExcecaoSeNecessario()

            var persistidos = dao.pesquisar(params)
            delegate?.let { persistidos = it.depoisDePesquisar(persistidos) }

            val resultado = persistidos.map { p ->
                var entidade = p
//                delegate?.let { d -> entidade = d.depoisDePesquisar(entidade) }

                delegate?.let { entidade = it.depoisDePesquisar(entidade) }

                val data = resClass.createInstance()
                data.preencherCom(entidade)
                data
            }

            return if (resultado.isEmpty()) null else resultado
        }

        fun <E : Versionado, O : ResponseData<E>> obter(@PathParam("id") id: UUID,
                                                        resClass: KClass<O>,
                                                        dao: CrudDAO<E>,
                                                        delegate: CrudRESTDelegate<E, RequestData<E>>? = null): Response {
            var persistido = carregar(id, dao)
            delegate?.let { d -> persistido = d.depoisDePesquisar(persistido) }

            val resultado = resClass.createInstance()
            resultado.preencherCom(persistido)

            val atualizadoEm = Date.from(persistido.atualizadoEm!!.toInstant())
            return Response.ok().entity(resultado).lastModified(atualizadoEm).build()
        }

        fun <E : Versionado, I : RequestData<E>, O : ResponseData<E>> inserir(data: I,
                                                                              type: KClass<E>,
                                                                              resClass: KClass<O>,
                                                                              dao: CrudDAO<E>,
                                                                              delegate: CrudRESTDelegate<E, I>? = null): Response {
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


//            return inserir(data, type, resClass, dao, { e, d ->
//                delegate?.let { it.antesDePersistir(e, d) }
////                lancarExcecaoSeNecessario()
//            }, { e, d ->
//                delegate?.let { it.depoisDePersistir(e, d) }
////                lancarExcecaoSeNecessario()
//            })
        }

//        private fun <E : Versionado, I : Requested<E>, O : Responsed<E>> inserir(data: I,
//                                                                                 type: KClass<E>,
//                                                                                 resClass: KClass<O>,
//                                                                                 dao: CrudDAO<E>,
//                                                                                 antes: ((E, I) -> Unit)? = null,
//                                                                                 depois: ((E, I) -> Unit)? = null): Response {
//            val entidade = novaEntidade(type)
//            data.escreverEm(entidade)
//
//            antes?.invoke(entidade, data)
//            dao.inserir(entidade)
//            depois?.invoke(entidade, data)
//
//            val uriInfo = ResteasyProviderFactory.getInstance().getContextData(UriInfo::class.java)
//            val location = uriInfo.requestUriBuilder.path("${entidade.id}").build()
//            val responseData = resClass.createInstance()
//            responseData.preencherCom(entidade)
//
//            val atualizadoEm = Date.from(entidade.atualizadoEm!!.toInstant())
//            return Response.created(location).entity(responseData).lastModified(atualizadoEm).build()
//        }

        fun <E : Versionado, I : RequestData<E>, O : ResponseData<E>> atualizar(id: UUID, data: I,
                                                                                resClass: KClass<O>,
                                                                                dao: CrudDAO<E>,
                                                                                delegate: CrudRESTDelegate<E, I>? = null): Response {
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

        fun <E : Versionado, O : ResponseData<E>> deletar(@PathParam("id") id: UUID,
                                                          resClass: KClass<O>,
                                                          dao: CrudDAO<E>,
                                                          delegate: CrudRESTDelegate<E, RequestData<E>>? = null): O {
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