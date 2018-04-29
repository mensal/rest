package rest.service

import core.entity.PagamentoFixa
import core.entity.TipoDespesaFixa
import core.persistence.PagamentoFixaDAO
import core.persistence.TipoDespesaFixaDAO
import rest.data.PagamentoFixaReqData
import rest.data.PagamentoFixaResData
import javax.ws.rs.Path

@Path("pagamento/fixas")
open class PagamentoFixasREST : PagamentoREST<PagamentoFixa, TipoDespesaFixa, PagamentoFixaReqData, PagamentoFixaResData, PagamentoFixaDAO, TipoDespesaFixaDAO>()