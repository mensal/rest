package app.rest.service

import app.core.entity.TipoDespesaDiarista
import app.core.persistence.TipoDespesaDiaristaDAO
import app.rest.data.TipoDespesaDiaristaReqData
import app.rest.data.TipoDespesaDiaristaResData
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/tipo/diaristas")
class TipoDespesaDiaristasREST : CrudREST<TipoDespesaDiarista, TipoDespesaDiaristaReqData, TipoDespesaDiaristaResData, TipoDespesaDiaristaDAO>() {

    override fun antesDeExcluir(entidade: TipoDespesaDiarista) {
//        if (!PagamentoDiaristaDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
    }
}
