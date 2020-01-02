package rest.service

import core.entity.TipoDespesaCombustivel
import core.persistence.TipoDespesaCombustivelDAO
import org.jboss.resteasy.annotations.GZIP
import rest.data.TipoDespesaCombustivelReqData
import rest.data.TipoDespesaCombustivelResData
import rest.security.Logado
import rest.service.CrudREST.Companion.atualizar
import rest.service.CrudREST.Companion.deletar
import rest.service.CrudREST.Companion.inserir
import rest.service.CrudREST.Companion.obter
import rest.service.CrudREST.Companion.pesquisar
import rest.service.CrudREST.Companion.uuidRegex
import java.util.*
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*

@Path("tipo/combustiveis")
@Transactional(rollbackOn = [Throwable::class])
class TipoDespesaCombustiveisREST /*: CrudREST<TipoDespesaCombustivel, TipoDespesaCombustivelReqData, TipoDespesaCombustivelResData, TipoDespesaCombustivelDAO>*/ {

    private val entityType = TipoDespesaCombustivel::class
    private val responseType = TipoDespesaCombustivelResData::class

    @Inject
    lateinit var dao: TipoDespesaCombustivelDAO

//    override fun antesDeExcluir(entidade: TipoDespesaCombustivel) {
////        if (!PagamentoCombustivelDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
//    }

    @GET
    @GZIP
    @Logado
    @Produces("application/json")
    fun pesquisar() = pesquisar(responseType, dao)

    @GET
    @Logado
    @Path("{id: $uuidRegex}")
    @Produces("application/json")
    fun obter(@PathParam("id") id: UUID) = obter(id, responseType, dao)

    @PUT
    @Logado
    @Path("{id: $uuidRegex}")
    @Consumes("application/json")
    @Produces("application/json")
    fun atualizar(@PathParam("id") id: UUID, @Valid data: TipoDespesaCombustivelReqData) = atualizar(id, data, responseType, dao)

    @POST
    @Logado
    @Consumes("application/json")
    @Produces("application/json")
    fun inserir(@Valid data: TipoDespesaCombustivelReqData) = inserir(data, entityType, responseType, dao)

    @DELETE
    @Logado
    @Path("{id: $uuidRegex}")
    @Produces("application/json")
    fun deletar(@PathParam("id") id: UUID) = deletar(id, responseType, dao)
}