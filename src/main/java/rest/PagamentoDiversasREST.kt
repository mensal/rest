package rest

import core.entity.PagamentoDiversa
import core.persistence.PagamentoDiversaDAO
import rest.data.PagamentoDiversaReqData
import rest.data.PagamentoDiversaResData
import javax.inject.Inject
import javax.ws.rs.Path

@Path("pagamento/diversas")
open class PagamentoDiversasREST : CrudREST<PagamentoDiversa, PagamentoDiversaReqData, PagamentoDiversaResData, PagamentoDiversaDAO>() {

    @Inject
    override lateinit var dao: PagamentoDiversaDAO

    override fun newEntity() = PagamentoDiversa()

    override fun newRequestData() = PagamentoDiversaReqData()

    override fun newResponseData() = PagamentoDiversaResData()
}