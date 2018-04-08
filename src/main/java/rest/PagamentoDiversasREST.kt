package rest

import br.gov.serpro.ssdk.rest.ClientViolationException
import core.entity.PagamentoDiversa
import core.persistence.PagamentoDiversaDAO
import core.persistence.TipoDespesaDiversaDAO
import rest.data.PagamentoDiversaReqData
import rest.data.PagamentoDiversaResData
import javax.enterprise.inject.spi.CDI
import javax.ws.rs.Path

@Path("pagamento/diversas")
open class PagamentoDiversasREST : CrudREST<PagamentoDiversa, PagamentoDiversaReqData, PagamentoDiversaResData, PagamentoDiversaDAO> {

    override fun novaEntidade() = PagamentoDiversa()

    override fun novoRequestData() = PagamentoDiversaReqData()

    override fun novoResponseData() = PagamentoDiversaResData()

    override fun daoClass() = PagamentoDiversaDAO::class.java

    override fun antesDePersistir(entidade: PagamentoDiversa, dataRequest: PagamentoDiversaReqData) {
        val tipoDAO = CDI.current().select(TipoDespesaDiversaDAO::class.java).select().get()!!
        val tipo = tipoDAO.obter(dataRequest.tipo.id)


        entidade.tipo = tipo ?: throw ClientViolationException(422).addViolation("tipo.id", "tipo de despesa inválido")
    }
}