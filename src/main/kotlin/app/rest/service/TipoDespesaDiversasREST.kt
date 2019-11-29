package app.rest.service

import app.core.entity.TipoDespesaDiversa
import app.core.persistence.TipoDespesaDiversaDAO
import app.rest.data.TipoDespesaDiversaReqData
import app.rest.data.TipoDespesaDiversaResData
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/tipo/diversas")
class TipoDespesaDiversasREST : CrudREST<TipoDespesaDiversa, TipoDespesaDiversaReqData, TipoDespesaDiversaResData, TipoDespesaDiversaDAO>() {

    override fun antesDeExcluir(entidade: TipoDespesaDiversa) {
//        if (!PagamentoDiversaDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
    }
}