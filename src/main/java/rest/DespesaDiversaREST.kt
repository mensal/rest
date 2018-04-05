package rest

import core.entity.Periodo
import core.entity.TipoDespesaDiversa
import core.persistence.DespesaDiversaDAO
import rest.data.DespesaDiversaData
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@Path("despesas/diversas")
internal open class DespesaDiversaREST {

    private val dao by lazy { DespesaDiversaDAO.instance() }

    @GET
    @Produces("application/json")
    open fun pesquisar(): List<DespesaDiversaData>? {
        val resultado = dao.pesquisar().map { DespesaDiversaData().ler(it) }
        return if (resultado.isEmpty()) null else resultado
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    open fun obter(@PathParam("id") id: UUID) = DespesaDiversaData().ler(carregar(id))

    @POST
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun inserir(@Valid data: DespesaDiversaData, @Context uriInfo: UriInfo): Response {
        val despesa = data.escrever(TipoDespesaDiversa())!!

        if (data.periodo == null) despesa.periodo = Periodo()
        if (data.periodo?.de == null) despesa.periodo?.de = Date()

        dao.inserir(despesa)

        val location = uriInfo.requestUriBuilder.path("${despesa.id}").build()
        val entity = DespesaDiversaData().ler(despesa)

        return Response.created(location).entity(entity).build()
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    open fun atualizar(@PathParam("id") id: UUID, @Valid data: DespesaDiversaData): DespesaDiversaData {
        data.id = id

        val persistido = data.escrever(carregar(id))!!
        dao.atualizar(persistido)

        return DespesaDiversaData().ler(persistido)
    }

    private fun carregar(id: UUID) = dao.obter(id) ?: throw NotFoundException()
}