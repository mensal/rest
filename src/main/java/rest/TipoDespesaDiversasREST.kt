package rest

import core.entity.TipoDespesaDiversa
import core.persistence.TipoDespesaDiversaDAO
import rest.data.TipoDespesaDiversaData
import javax.inject.Inject
import javax.ws.rs.Path


@Path("tipo/diversas")
open class TipoDespesaDiversasREST : CrudREST<TipoDespesaDiversa, TipoDespesaDiversaData, TipoDespesaDiversaDAO>() {

    @Inject
    override lateinit var dao: TipoDespesaDiversaDAO

    override fun newEntity() = TipoDespesaDiversa()

    override fun newData() = TipoDespesaDiversaData()

//    @GET
//    @Produces("application/json")
//    override fun pesquisar(): List<TipoDespesaDiversaData>? {
//        val resultado = dao.pesquisar().map { TipoDespesaDiversaData().ler(it) }
//        return if (resultado.isEmpty()) null else resultado
//    }

//    @POST
//    @Transactional
//    @Consumes("application/json")
//    @Produces("application/json")
//    override fun inserir(@Valid data: TipoDespesaDiversaData, @Context uriInfo: UriInfo, antesDeInserir: ((TipoDespesaDiversa) -> Unit)?): Response {
//        return super.inserir(data, uriInfo, { e ->
//            if (data.periodo == null) e.periodo = Periodo()
//            if (data.periodo?.de == null) e.periodo?.de = Date()
//        })
//    }

//    @POST
//    @Transactional
//    @Consumes("application/json")
//    @Produces("application/json")
//    override fun inserir(@Valid data: TipoDespesaDiversaData, @Context uriInfo: UriInfo): Response {
//        val tipo = data.escrever(TipoDespesaDiversa())!!
//
//        if (data.periodo == null) tipo.periodo = Periodo()
//        if (data.periodo?.de == null) tipo.periodo?.de = Date()
//
//        dao.inserir(tipo)
//
//        val location = uriInfo.requestUriBuilder.path("${tipo.id}").build()
//        val entity = TipoDespesaDiversaData().ler(tipo)
//
//        return Response.created(location).entity(entity).build()
//    }

//    @GET
//    @Path("{id}")
//    @Produces("application/json")
//    override fun obter(@PathParam("id") id: UUID): Response {
//        var persistido = carregar(id)
//        val resultado = TipoDespesaDiversaData().ler(persistido)
//
//        return Response.ok().entity(resultado).lastModified(persistido.atualizadoEm).build()
//    }

//    @PUT
//    @Path("{id}")
//    @Transactional
//    @Consumes("application/json")
//    @Produces("application/json")
//    override fun atualizar(@PathParam("id") id: UUID, @Valid data: TipoDespesaDiversaData, @Context headers: HttpHeaders, @Context request: Request): Response {
//        var persistido = carregar(id)
//        var builder = buildIfModified(request, headers, persistido)
//
//        if (builder == null) {
//            data.id = id
//            persistido = dao.atualizar(data.escrever(persistido)!!)
//            builder = Response.ok().entity(TipoDespesaDiversaData().ler(persistido)).lastModified(persistido.atualizadoEm)
//        }
//
//        return builder.build()
//    }

//    private fun carregar(id: UUID) = dao.obter(id) ?: throw NotFoundException()
}