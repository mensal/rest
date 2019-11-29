package app.rest.service

import app.core.entity.PagamentoDiversa
import app.core.entity.TipoDespesaDiversa
import app.core.persistence.PagamentoDiversaDAO
import app.core.persistence.TipoDespesaDiversaDAO
import app.rest.data.PagamentoDiversaReqData
import app.rest.data.PagamentoDiversaResData
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/pagamento/diversas")
class PagamentoDiversasREST : PagamentoREST<PagamentoDiversa, TipoDespesaDiversa, PagamentoDiversaReqData, PagamentoDiversaResData, PagamentoDiversaDAO, TipoDespesaDiversaDAO>()