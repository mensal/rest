package app.rest.service

import app.core.entity.TipoDespesaCombustivel
import app.core.persistence.TipoDespesaCombustivelDAO
import app.rest.data.TipoDespesaCombustivelReqData
import app.rest.data.TipoDespesaCombustivelResData
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/tipo/combustiveis")
class TipoDespesaCombustiveisREST : CrudREST<TipoDespesaCombustivel, TipoDespesaCombustivelReqData, TipoDespesaCombustivelResData, TipoDespesaCombustivelDAO>() {

    override fun antesDeExcluir(entidade: TipoDespesaCombustivel) {
//        if (!PagamentoCombustivelDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
    }
}