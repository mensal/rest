package rest.service

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

    override fun novaEntidade() = TipoDespesaDiversa()

    override fun novoRequestData() = TipoDespesaDiversaReqData()

    override fun novoResponseData() = TipoDespesaDiversaResData()
}