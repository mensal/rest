package rest.service

import core.entity.TipoDespesaDiarista
import core.persistence.TipoDespesaDiaristaDAO
import org.jboss.resteasy.annotations.GZIP
import rest.data.TipoDespesaDiaristaReqData
import rest.data.TipoDespesaDiaristaResData
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

@Path("tipo/diaristas")
@Transactional(rollbackOn = [Throwable::class])
class TipoDespesaDiaristasREST /*: CrudREST<TipoDespesaDiarista, TipoDespesaDiaristaReqData, TipoDespesaDiaristaResData, TipoDespesaDiaristaDAO>*/ {

    private val entityType = TipoDespesaDiarista::class
    private val responseType = TipoDespesaDiaristaResData::class

    @Inject
    lateinit var dao: TipoDespesaDiaristaDAO

//    fun antesDeExcluir(entidade: TipoDespesaDiarista) {
////        if (!PagamentoDiaristaDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
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
    fun atualizar(@PathParam("id") id: UUID, @Valid data: TipoDespesaDiaristaReqData) = atualizar(id, data, responseType, dao)

    @POST
    @Logado
    @Consumes("application/json")
    @Produces("application/json")
    fun inserir(@Valid data: TipoDespesaDiaristaReqData) = inserir(data, entityType, responseType, dao)

    @DELETE
    @Logado
    @Path("{id: $uuidRegex}")
    @Produces("application/json")
    fun deletar(@PathParam("id") id: UUID) = deletar(id, responseType, dao)
}
