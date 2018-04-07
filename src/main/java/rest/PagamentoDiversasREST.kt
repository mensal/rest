package rest

import core.entity.PagamentoDiversa
import core.persistence.PagamentoDiversaDAO
import rest.data.PagamentoDiversaData
import javax.inject.Inject
import javax.ws.rs.Path

@Path("pagamento/diversas")
open class PagamentoDiversasREST : CrudREST<PagamentoDiversa, PagamentoDiversaData, PagamentoDiversaDAO>() {

    @Inject
    override lateinit var dao: PagamentoDiversaDAO

    override fun newEntity() = PagamentoDiversa()

    override fun newData() = PagamentoDiversaData()
}