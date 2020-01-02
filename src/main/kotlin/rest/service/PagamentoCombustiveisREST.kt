package rest.service

import core.entity.PagamentoCombustivel
import core.entity.TipoDespesaCombustivel
import core.persistence.PagamentoCombustivelDAO
import core.persistence.TipoDespesaCombustivelDAO
import org.jboss.resteasy.annotations.GZIP
import rest.data.PagamentoCombustivelReqData
import rest.data.PagamentoCombustivelResData
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

@Path("pagamento/combustiveis")
@Transactional(rollbackOn = [Throwable::class])
class PagamentoCombustiveisREST : PagamentoRESTDelegate<PagamentoCombustivel, PagamentoCombustivelReqData, TipoDespesaCombustivel, PagamentoCombustivelDAO, TipoDespesaCombustivelDAO>() {

    private val entityType = PagamentoCombustivel::class
    private val responseType = PagamentoCombustivelResData::class

    @Inject
    override lateinit var dao: PagamentoCombustivelDAO

    @Inject
    override lateinit var tipoDAO: TipoDespesaCombustivelDAO

    @GET
    @GZIP
    @Logado
    @Produces("application/json")
    fun pesquisar() = pesquisar(responseType, dao, this)

    @GET
    @Logado
    @Path("{id: $uuidRegex}")
    @Produces("application/json")
    fun obter(@PathParam("id") id: UUID) = obter(id, responseType, dao, this)

    @PUT
    @Logado
    @Path("{id: $uuidRegex}")
    @Consumes("application/json")
    @Produces("application/json")
    fun atualizar(@PathParam("id") id: UUID, @Valid data: PagamentoCombustivelReqData) = atualizar(id, data, responseType, dao, this)

    @POST
    @Logado
    @Consumes("application/json")
    @Produces("application/json")
    fun inserir(@Valid data: PagamentoCombustivelReqData) = inserir(data, entityType, responseType, dao, this)

    @DELETE
    @Logado
    @Path("{id: $uuidRegex}")
    @Produces("application/json")
    fun deletar(@PathParam("id") id: UUID) = deletar(id, responseType, dao, this)
}