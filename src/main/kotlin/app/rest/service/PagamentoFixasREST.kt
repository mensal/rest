package app.rest.service

import app.core.entity.PagamentoFixa
import app.core.entity.TipoDespesaFixa
import app.core.persistence.PagamentoFixaDAO
import app.core.persistence.TipoDespesaFixaDAO
import app.rest.data.PagamentoFixaReqData
import app.rest.data.PagamentoFixaResData
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/pagamento/fixas")
class PagamentoFixasREST : PagamentoREST<PagamentoFixa, TipoDespesaFixa, PagamentoFixaReqData, PagamentoFixaResData, PagamentoFixaDAO, TipoDespesaFixaDAO>()