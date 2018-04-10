package rest.service

import core.entity.PagamentoDiversa
import core.entity.TipoDespesaDiversa
import core.persistence.PagamentoDiversaDAO
import core.persistence.TipoDespesaDiversaDAO
import rest.data.PagamentoDiversaReqData
import rest.data.PagamentoDiversaResData
import javax.inject.Inject
import javax.ws.rs.Path

@Path("pagamento/diversas")
open class PagamentoDiversasREST : PagamentoCrudREST<PagamentoDiversa, TipoDespesaDiversa, PagamentoDiversaReqData, PagamentoDiversaResData, PagamentoDiversaDAO>() {

    @Inject
    override lateinit var dao: PagamentoDiversaDAO

    override fun novaEntidade() = PagamentoDiversa()

    override fun novoRequestData() = PagamentoDiversaReqData()

    override fun novoResponseData() = PagamentoDiversaResData()

    override fun antesDePersistir(entidade: PagamentoDiversa, requestData: PagamentoDiversaReqData) {
        val tipo = TipoDespesaDiversaDAO.instance().obter(requestData.tipo.id)
        if (tipo != null) entidade.tipo = tipo else violationException.addViolation("tipo.id", "tipo de despesa inválido")
    }

}