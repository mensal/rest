package app.rest.service

import app.core.entity.PagamentoCombustivel
import app.core.entity.TipoDespesaCombustivel
import app.core.persistence.PagamentoCombustivelDAO
import app.core.persistence.TipoDespesaCombustivelDAO
import app.rest.data.PagamentoCombustivelReqData
import app.rest.data.PagamentoCombustivelResData
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/pagamento/combustiveis")
class PagamentoCombustiveisREST : PagamentoREST<PagamentoCombustivel, TipoDespesaCombustivel, PagamentoCombustivelReqData, PagamentoCombustivelResData, PagamentoCombustivelDAO, TipoDespesaCombustivelDAO>()