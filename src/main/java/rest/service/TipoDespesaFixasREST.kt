package rest.service

import core.entity.TipoDespesaFixa
import core.persistence.PagamentoFixaDAO
import core.persistence.TipoDespesaFixaDAO
import rest.data.TipoDespesaFixaReqData
import rest.data.TipoDespesaFixaResData
import javax.inject.Inject
import javax.ws.rs.Path

@Path("tipo/fixas")
open class TipoDespesaFixasREST : CrudREST<TipoDespesaFixa, TipoDespesaFixaReqData, TipoDespesaFixaResData, TipoDespesaFixaDAO>() {

    @Inject
    override lateinit var dao: TipoDespesaFixaDAO

    override fun novaEntidade() = TipoDespesaFixa()

    override fun novoRequestData() = TipoDespesaFixaReqData()

    override fun novoResponseData() = TipoDespesaFixaResData()

    override fun antesDeExcluir(entidade: TipoDespesaFixa) {
        if (!PagamentoFixaDAO.instance().buscar(entidade).isEmpty()) violationException.addViolation("Existem pagamentos associados a este tipo de despesa")
    }
}