package rest.service

import core.entity.PagamentoDiarista
import core.entity.TipoDespesaDiarista
import core.persistence.PagamentoDiaristaDAO
import core.persistence.TipoDespesaDiaristaDAO
import org.jboss.resteasy.annotations.GZIP
import rest.data.PagamentoDiaristaReqData
import rest.data.PagamentoDiaristaResData
import rest.security.Logado
import rest.service.CrudREST.Companion.atualizar
import rest.service.CrudREST.Companion.deletar
import rest.service.CrudREST.Companion.inserir
import rest.service.CrudREST.Companion.obter
import rest.service.CrudREST.Companion.pesquisar
import rest.service.CrudREST.Companion.uuidRegex
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*

@Path("pagamento/diaristas")
@Transactional(rollbackOn = [Throwable::class])
class PagamentoDiaristaREST : PagamentoRESTDelegate<PagamentoDiarista, PagamentoDiaristaReqData, TipoDespesaDiarista, PagamentoDiaristaDAO, TipoDespesaDiaristaDAO>() {

    private val entityType = PagamentoDiarista::class
    private val responseType = PagamentoDiaristaResData::class

    @Inject
    override lateinit var dao: PagamentoDiaristaDAO

    @Inject
    override lateinit var tipoDAO: TipoDespesaDiaristaDAO

    @GET
    @Logado
    @Path("saldo")
    @Produces("application/json")
    fun saldo(@QueryParam("ano") ano: Int?, @QueryParam("mes") mes: Int?): BigDecimal {
//        valida(ano, mes)
        return dao.pagoAte(ano!!, mes!!) - dao.devidoAte(ano, mes)
    }

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
    fun atualizar(@PathParam("id") id: UUID, @Valid data: PagamentoDiaristaReqData) = atualizar(id, data, responseType, dao, this)

    @POST
    @Logado
    @Consumes("application/json")
    @Produces("application/json")
    fun inserir(@Valid data: PagamentoDiaristaReqData) = inserir(data, entityType, responseType, dao, this)

    @DELETE
    @Logado
    @Path("{id: $uuidRegex}")
    @Produces("application/json")
    fun deletar(@PathParam("id") id: UUID) = deletar(id, responseType, dao, this)
}