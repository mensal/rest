package rest

import core.entity.TipoDespesaDiversa
import core.persistence.TipoDespesaDiversaDAO
import rest.data.TipoDespesaDiversaReqData
import rest.data.TipoDespesaDiversaResData
import javax.ws.rs.Path

@Path("tipo/diversas")
open class TipoDespesaDiversasREST : CrudREST<TipoDespesaDiversa, TipoDespesaDiversaReqData, TipoDespesaDiversaResData, TipoDespesaDiversaDAO> {

    override fun novaEntidade() = TipoDespesaDiversa()

    override fun novoRequestData() = TipoDespesaDiversaReqData()

    override fun novoResponseData() = TipoDespesaDiversaResData()

    override fun daoClass() = TipoDespesaDiversaDAO::class.java
}