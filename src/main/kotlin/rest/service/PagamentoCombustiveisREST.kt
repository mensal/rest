package rest.service

import core.entity.PagamentoCombustivel
import core.entity.TipoDespesaCombustivel
import core.persistence.PagamentoCombustivelDAO
import core.persistence.TipoDespesaCombustivelDAO
import rest.data.PagamentoCombustivelReqData
import rest.data.PagamentoCombustivelResData
import javax.ws.rs.Path

@Path("api/pagamento/combustiveis")
class PagamentoCombustiveisREST : PagamentoREST<PagamentoCombustivel, TipoDespesaCombustivel, PagamentoCombustivelReqData, PagamentoCombustivelResData, PagamentoCombustivelDAO, TipoDespesaCombustivelDAO>()