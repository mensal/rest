package rest.service

import core.entity.PagamentoFixa
import core.entity.TipoDespesaFixa
import core.persistence.PagamentoFixaDAO
import core.persistence.TipoDespesaFixaDAO
import org.jboss.resteasy.annotations.GZIP
import rest.data.PagamentoFixaReqData
import rest.data.PagamentoFixaResData
import rest.security.Logado
import rest.service.CrudREST.Companion.atualizar
import rest.service.CrudREST.Companion.deletar
import rest.service.CrudREST.Companion.inserir
import rest.service.CrudREST.Companion.obter
import rest.service.CrudREST.Companion.pesquisar
import java.util.*
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*

@Path("pagamento/fixas")
@Transactional(rollbackOn = [Throwable::class])
class PagamentoFixasREST : PagamentoRESTDelegate<PagamentoFixa, PagamentoFixaReqData, TipoDespesaFixa, PagamentoFixaDAO, TipoDespesaFixaDAO>() /*PagamentoREST<PagamentoFixa, TipoDespesaFixa, PagamentoFixaReqData, PagamentoFixaResData, PagamentoFixaDAO, TipoDespesaFixaDAO>()*/ {

    private val entityType = PagamentoFixa::class
    private val responseType = PagamentoFixaResData::class

    @Inject
    override lateinit var dao: PagamentoFixaDAO

    @Inject
    override lateinit var tipoDAO: TipoDespesaFixaDAO

    @GET
    @GZIP
    @Logado
    @Produces("application/json")
    fun pesquisar() = pesquisar(responseType, dao)

    @GET
    @Logado
    @Path("{id: ${CrudREST.uuidRegex}}")
    @Produces("application/json")
    fun obter(@PathParam("id") id: UUID) = obter(id, responseType, dao)

    @PUT
    @Logado
    @Path("{id: ${CrudREST.uuidRegex}}")
    @Consumes("application/json")
    @Produces("application/json")
    fun atualizar(@PathParam("id") id: UUID, @Valid data: PagamentoFixaReqData) = atualizar(id, data, responseType, dao, this)

    @POST
    @Logado
    @Consumes("application/json")
    @Produces("application/json")
    fun inserir(@Valid data: PagamentoFixaReqData) = inserir(data, entityType, responseType, dao, this)

    @DELETE
    @Logado
    @Path("{id: ${CrudREST.uuidRegex}}")
    @Produces("application/json")
    fun deletar(@PathParam("id") id: UUID) = deletar(id, responseType, dao)
}