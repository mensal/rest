package rest.service

import core.entity.TipoDespesaDiversa
import core.persistence.TipoDespesaDiversaDAO
import org.jboss.resteasy.annotations.GZIP
import rest.data.TipoDespesaDiversaReqData
import rest.data.TipoDespesaDiversaResData
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

@Path("tipo/diversas")
@Transactional(rollbackOn = [Throwable::class])
class TipoDespesaDiversasREST /*: CrudREST<TipoDespesaDiversa, TipoDespesaDiversaReqData, TipoDespesaDiversaResData, TipoDespesaDiversaDAO>*/ {

    private val entityType = TipoDespesaDiversa::class
    private val responseType = TipoDespesaDiversaResData::class

    @Inject
    lateinit var dao: TipoDespesaDiversaDAO

//    override fun antesDeExcluir(entidade: TipoDespesaDiversa) {
////        if (!PagamentoDiversaDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
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
    fun atualizar(@PathParam("id") id: UUID, @Valid data: TipoDespesaDiversaReqData) = atualizar(id, data, responseType, dao)

    @POST
    @Logado
    @Consumes("application/json")
    @Produces("application/json")
    fun inserir(@Valid data: TipoDespesaDiversaReqData) = inserir(data, entityType, responseType, dao)

    @DELETE
    @Logado
    @Path("{id: $uuidRegex}")
    @Produces("application/json")
    fun deletar(@PathParam("id") id: UUID) = deletar(id, responseType, dao)
}