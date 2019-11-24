//package app.rest.service
//
//import app.core.entity.Versionado
//import app.core.persistence.CrudDAO
//import app.core.persistence.VersionadoCrudDAO
//import app.core.util.Reflections
//import org.apache.commons.lang.time.DateUtils
//import app.rest.ClientViolationException
//import app.rest.PreconditionFailedException
//import app.rest.UnprocessableEntityException
//import app.rest.data.ReqData
//import app.rest.data.ResData
//import app.rest.security.Logado
//import java.util.*
//import javax.enterprise.inject.spi.CDI
//import javax.transaction.Transactional
//import javax.validation.Valid
//import javax.ws.rs.*
//import javax.ws.rs.app.core.*
//import kotlin.reflect.KClass
//import kotlin.reflect.full.createInstance
//
//abstract class CrudREST<ENT : Versionado, REQ : ReqData<ENT>, RES : ResData<ENT>, DAO : CrudDAO<ENT>> {
//
//    protected open val violationException = UnprocessableEntityException()
//
//    protected open val entityClass: KClass<ENT>
//        get() = Reflections.argument(this, CrudREST::class, 0)
//
//    protected open val resClass: KClass<RES>
//        get() = Reflections.argument(this, CrudREST::class, 2)
//
//    protected open val daoClass: KClass<DAO>
//        get() = Reflections.argument(this, CrudREST::class, 3)
//
//    protected open val dao: DAO
//        get() = CDI.current().select(daoClass.java).get()!!
//
//    protected open fun novaEntidade() = entityClass.createInstance()
//
//    protected open fun novoResponseData() = resClass.createInstance()
//
//    protected open fun antesDePersistir(entidade: ENT, requestData: REQ) {}
//
//    protected open fun depoisDePersistir(entidade: ENT, requestData: REQ) {}
//
//    protected open fun antesDeExcluir(entidade: ENT) {}
//
//    protected open fun depoisDePesquisar(entidade: ENT) = entidade
//
//    protected open fun depoisDePesquisar(entidades: List<ENT>) = entidades
//
//    @GET
//    @Logado
//    @Produces("application/json")
//    open fun pesquisar(@Context uriInfo: UriInfo): List<RES>? {
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
//    @POST
//    @Logado
//    @Consumes("application/json")
//    @Produces("application/json")
//    @Transactional(rollbackOn = [Throwable::class])
//    open fun inserir(@Valid data: REQ, @Context uriInfo: UriInfo): Response {
//        val entidade = novaEntidade()
//
//        return inserir(uriInfo, data, resClass as KClass<ResData<ENT>>, entidade, daoClass as KClass<VersionadoCrudDAO<ENT>>, { e, d ->
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
//    open fun atualizar(@PathParam("id") id: UUID, @Valid data: REQ, @Context headers: HttpHeaders, @Context request: Request): Response {
//        var persistido = carregar(id)
//        var builder = buildSeModificado(request, headers, persistido)
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
//    open fun deletar(@PathParam("id") id: UUID): RES {
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
//
//    companion object {
//        const val uuidRegex = "\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}"
//
//        fun <E : Versionado, D : VersionadoCrudDAO<E>> dao(daoClass: KClass<D>) = CDI.current().select(daoClass.java).get()!!
//
//        fun <E : Versionado, R : ReqData<E>> inserir(uriInfo: UriInfo,
//                                                     reqData: R,
//                                                     resClass: KClass<ResData<E>>,
//                                                     entidade: E,
//                                                     daoClass: KClass<VersionadoCrudDAO<E>>,
//                                                     antes: ((E, R) -> Unit)? = null,
//                                                     depois: ((E, R) -> Unit)? = null): Response {
//            reqData.escreverEm(entidade)
//
//            antes?.invoke(entidade, reqData)
//            dao(daoClass).inserir(entidade)
//            depois?.invoke(entidade, reqData)
//
//            val location = uriInfo.requestUriBuilder.path("${entidade.id}").build()
//            val responseData = resClass.createInstance()
//            responseData.preencherCom(entidade)
//
//            val atualizadoEm = Date.from(entidade.atualizadoEm!!.toInstant())
//            return Response.created(location).entity(responseData).lastModified(atualizadoEm).build()
//        }
//
////        fun valida(ano: Int?, mes: Int?, exception: ClientViolationException) {
////            if (ano == null) exception.addViolation("ano", "parâmetro obrigatório")
////            if (mes == null) exception.addViolation("mes", "parâmetro obrigatório")
////        }
//
//        private fun buildSeModificado(request: Request, headers: HttpHeaders, versionado: Versionado): Response.ResponseBuilder? {
//            headers.getHeaderString("If-Unmodified-Since") ?: throw PreconditionFailedException()
//
//            return try {
//                val atualizadoEm = Date.from(versionado.atualizadoEm!!.toInstant())
//                request.evaluatePreconditions(DateUtils.truncate(atualizadoEm, Calendar.SECOND))
//            } catch (cause: Exception) {
////                cause.printStackTrace()
//                throw PreconditionFailedException()
//            }
//        }
//
//        fun lancarExcecaoSeNecessario(exception: ClientViolationException) {
//            if (!exception.violations.isEmpty()) throw exception
//        }
//    }
//}