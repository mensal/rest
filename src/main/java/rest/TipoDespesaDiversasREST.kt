package rest

import core.entity.TipoDespesaDiversa
import core.persistence.TipoDespesaDiversaDAO
import rest.data.TipoDespesaDiversaReqData
import rest.data.TipoDespesaDiversaResData
import javax.inject.Inject
import javax.ws.rs.Path

@Path("tipo/diversas")
open class TipoDespesaDiversasREST : CrudREST<TipoDespesaDiversa, TipoDespesaDiversaReqData, TipoDespesaDiversaResData, TipoDespesaDiversaDAO>() {

    @Inject
    override lateinit var dao: TipoDespesaDiversaDAO

    override fun newEntity() = TipoDespesaDiversa()

    override fun newRequestData() = TipoDespesaDiversaReqData()

    override fun newResponseData() = TipoDespesaDiversaResData()
}