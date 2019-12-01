package app.rest.service

import app.core.entity.Versionado
import app.core.persistence.CrudDAO
import app.core.util.Reflections
import app.core.util.autowired
import app.rest.ClientViolationException
import app.rest.NotFoundException
import app.rest.PreconditionFailedException
import app.rest.UnprocessableEntityException
import app.rest.data.ReqData
import app.rest.data.ResData
import app.rest.security.Logado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.WebApplicationContext.*
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.support.ServletUriComponentsBuilder.*
import java.util.*
import javax.validation.Valid
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class CrudREST<ENT : Versionado, REQ : ReqData<ENT>, RES : ResData<ENT>, DAO : CrudDAO<ENT>> {

//    @Autowired
//    protected open lateinit var violationException: UnprocessableEntityException

    protected open val violationException: UnprocessableEntityException
        get() = autowired(UnprocessableEntityException::class)

    protected open val entityClass: KClass<ENT>
        get() = Reflections.argument(this, CrudREST::class, 0)

    protected open val resClass: KClass<RES>
        get() = Reflections.argument(this, CrudREST::class, 2)

    protected open val daoClass: KClass<DAO>
        get() = Reflections.argument(this, CrudREST::class, 3)

    protected open val dao: DAO
        get() = autowired(daoClass)

    protected open fun novaEntidade() = entityClass.createInstance()

    protected open fun novoResponseData() = resClass.createInstance()

    protected open fun antesDePersistir(entidade: ENT, requestData: REQ) {}

    protected open fun depoisDePersistir(entidade: ENT, requestData: REQ) {}

    protected open fun antesDeExcluir(entidade: ENT) {}

    protected open fun depoisDePesquisar(entidade: ENT) = entidade

    protected open fun depoisDePesquisar(entidades: List<ENT>) = entidades

    protected open fun validaParametrosDePesquisa(params: Map<String, String>, exception: ClientViolationException) {}

    @Logado
    @GetMapping
    open fun pesquisar(@RequestParam params: Map<String, String>): List<RES>? {
        validaParametrosDePesquisa(params, violationException)
        lancarExcecaoSeNecessario()

        var persistidos = dao.pesquisar(params)
        persistidos = depoisDePesquisar(persistidos)

        return persistidos.map {
            val entidade = depoisDePesquisar(it)

            val data = novoResponseData()
            data.preencherCom(entidade)
            data
        }
    }

    @Logado
    @GetMapping("{id:$uuidRegex}")
    open fun obter(@PathVariable id: UUID): ResponseEntity<RES> {
        var persistido = carregar(id)
        persistido = depoisDePesquisar(persistido)

        val resultado = novoResponseData()
        resultado.preencherCom(persistido)

        return ResponseEntity.ok().lastModified(persistido.atualizadoEm!!).body(resultado)
    }

    @Logado
    @PostMapping
    @Transactional(rollbackFor = [Throwable::class])
    open fun inserir(@RequestBody @Valid data: REQ): ResponseEntity<RES> {
        val entidade = novaEntidade()

        return inserir(data, resClass, entidade, daoClass, { e, d ->
            antesDePersistir(e, d)
            lancarExcecaoSeNecessario()
        }, { e, d ->
            depoisDePersistir(e, d)
            lancarExcecaoSeNecessario()
        })
    }

    @Logado
    @PutMapping("{id:$uuidRegex}")
    @Transactional(rollbackFor = [Throwable::class])
    open fun atualizar(@PathVariable id: UUID, @RequestBody @Valid data: REQ, request: WebRequest): ResponseEntity<RES> {
        var persistido = carregar(id)
        validaSeModificado(request, persistido)

        data.escreverEm(persistido)

        antesDePersistir(persistido, data)
        persistido = dao.atualizar(persistido)
        depoisDePersistir(persistido, data)
        lancarExcecaoSeNecessario()

        val responseData = novoResponseData()
        responseData.preencherCom(persistido)

        return ResponseEntity.ok().lastModified(persistido.atualizadoEm!!).body(responseData)
    }

    @Logado
    @DeleteMapping("{id:$uuidRegex}")
    @Transactional(rollbackFor = [Throwable::class])
    open fun deletar(@PathVariable id: UUID, request: WebRequest): RES {
        val persistido = carregar(id)
        validaSeModificado(request, persistido)

        antesDeExcluir(persistido)
        dao.excluir(persistido)
        lancarExcecaoSeNecessario()

        val responseData = novoResponseData()
        responseData.preencherCom(persistido)

        return responseData
    }

    private fun carregar(id: UUID) = dao.obter(id) ?: throw NotFoundException()

//    protected open fun valida(params: Map<String, String>) {
////        Companion.valida(params, violationException)
//    }

    protected open fun lancarExcecaoSeNecessario() {
        lancarExcecaoSeNecessario(violationException)
    }

    companion object {
        const val uuidRegex = "\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}"

        private fun <E : Versionado, D : CrudDAO<E>> dao(daoClass: KClass<D>) = autowired(daoClass)

        fun <E : Versionado, R : ReqData<E>, S : ResData<E>, D : CrudDAO<E>> inserir(reqData: R,
                                                                                     resClass: KClass<S>,
                                                                                     entidade: E,
                                                                                     daoClass: KClass<D>,
                                                                                     antes: ((E, R) -> Unit)? = null,
                                                                                     depois: ((E, R) -> Unit)? = null): ResponseEntity<S> {
            reqData.escreverEm(entidade)

            antes?.invoke(entidade, reqData)
            dao(daoClass).inserir(entidade)
            depois?.invoke(entidade, reqData)

            val location = fromCurrentRequest().path("/${entidade.id}").build()
            val responseData = resClass.createInstance()
            responseData.preencherCom(entidade)

            return ResponseEntity.created(location.toUri()).lastModified(entidade.atualizadoEm!!).body(responseData)
        }

        private fun validaSeModificado(request: WebRequest, versionado: Versionado) {
            if (request.checkNotModified(versionado.atualizadoEm!!.toInstant().toEpochMilli())) throw PreconditionFailedException()
        }

        fun lancarExcecaoSeNecessario(exception: ClientViolationException) {
            if (exception.violations.isNotEmpty()) throw exception
        }
    }
}