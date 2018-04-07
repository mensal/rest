package rest

import core.entity.PagamentoDiversa
import core.persistence.PagamentoDiversaDAO
import rest.data.PagamentoDiversaRequestData
import rest.data.PagamentoDiversaResponseData
import javax.inject.Inject
import javax.ws.rs.Path

@Path("pagamento/diversas")
open class PagamentoDiversasREST : CrudREST<PagamentoDiversa, PagamentoDiversaRequestData, PagamentoDiversaResponseData, PagamentoDiversaDAO>() {

    @Inject
    override lateinit var dao: PagamentoDiversaDAO

    override fun newEntity() = PagamentoDiversa()

    override fun newRequestData() = PagamentoDiversaRequestData()

    override fun newResponseData() = PagamentoDiversaResponseData()
}