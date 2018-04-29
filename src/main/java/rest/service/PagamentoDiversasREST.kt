package rest.service

import core.entity.PagamentoDiversa
import core.entity.TipoDespesaDiversa
import core.persistence.PagamentoDiversaDAO
import core.persistence.TipoDespesaDiversaDAO
import rest.data.PagamentoDiversaReqData
import rest.data.PagamentoDiversaResData
import javax.ws.rs.Path

@Path("pagamento/diversas")
open class PagamentoDiversasREST : PagamentoREST<PagamentoDiversa, TipoDespesaDiversa, PagamentoDiversaReqData, PagamentoDiversaResData, PagamentoDiversaDAO, TipoDespesaDiversaDAO>()