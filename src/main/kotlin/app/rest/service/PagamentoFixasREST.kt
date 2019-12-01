package app.rest.service

import app.core.entity.PagamentoFixa
import app.core.entity.TipoDespesaFixa
import app.core.persistence.PagamentoFixaDAO
import app.core.persistence.TipoDespesaFixaDAO
import app.rest.data.PagamentoFixaReqData
import app.rest.data.PagamentoFixaResData
import org.springframework.context.annotation.Scope
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST

@RestController
@RequestMapping("api/pagamento/fixas")
class PagamentoFixasREST : PagamentoREST<PagamentoFixa, TipoDespesaFixa, PagamentoFixaReqData, PagamentoFixaResData, PagamentoFixaDAO, TipoDespesaFixaDAO>()