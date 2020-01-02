package rest.service

import core.entity.PagamentoDiversa
import core.entity.TipoDespesaDiversa
import core.persistence.PagamentoDiversaDAO
import core.persistence.TipoDespesaDiversaDAO
import org.jboss.resteasy.annotations.GZIP
import rest.data.PagamentoDiversaReqData
import rest.data.PagamentoDiversaResData
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

@Path("pagamento/diversas")
@Transactional(rollbackOn = [Throwable::class])
class PagamentoDiversasREST : PagamentoRESTDelegate<PagamentoDiversa, PagamentoDiversaReqData, TipoDespesaDiversa, PagamentoDiversaDAO, TipoDespesaDiversaDAO>() {

    private val entityType = PagamentoDiversa::class
    private val responseType = PagamentoDiversaResData::class

    @Inject
    override lateinit var dao: PagamentoDiversaDAO

    @Inject
    override lateinit var tipoDAO: TipoDespesaDiversaDAO

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
    fun atualizar(@PathParam("id") id: UUID, @Valid data: PagamentoDiversaReqData) = atualizar(id, data, responseType, dao, this)

    @POST
    @Logado
    @Consumes("application/json")
    @Produces("application/json")
    fun inserir(@Valid data: PagamentoDiversaReqData) = inserir(data, entityType, responseType, dao, this)

    @DELETE
    @Logado
    @Path("{id: $uuidRegex}")
    @Produces("application/json")
    fun deletar(@PathParam("id") id: UUID) = deletar(id, responseType, dao, this)
}