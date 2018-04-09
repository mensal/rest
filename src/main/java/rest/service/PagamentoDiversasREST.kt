package rest.service

import core.entity.PagamentoDiversa
import core.entity.UsuarioPagamento
import core.persistence.PagamentoDiversaDAO
import core.persistence.TipoDespesaDiversaDAO
import core.persistence.UsuarioDAO
import core.persistence.UsuarioPagamentoDAO
import rest.data.PagamentoDiversaReqData
import rest.data.PagamentoDiversaResData
import javax.inject.Inject
import javax.ws.rs.Path

@Path("pagamento/diversas")
open class PagamentoDiversasREST : CrudREST<PagamentoDiversa, PagamentoDiversaReqData, PagamentoDiversaResData, PagamentoDiversaDAO>() {

    @Inject
    override lateinit var dao: PagamentoDiversaDAO

    override fun novaEntidade() = PagamentoDiversa()

    override fun novoRequestData() = PagamentoDiversaReqData()

    override fun novoResponseData() = PagamentoDiversaResData()

    override fun antesDePersistir(entidade: PagamentoDiversa, requestData: PagamentoDiversaReqData) {
        val tipoDAO = TipoDespesaDiversaDAO.instance()

        val tipo = tipoDAO.obter(requestData.tipo.id)
        if (tipo != null) entidade.tipo = tipo else violationException.addViolation("tipo.id", "tipo de despesa inválido")
    }

    override fun depoisDePersistir(entidade: PagamentoDiversa, requestData: PagamentoDiversaReqData) {
        val usuarioDAO = UsuarioDAO.instance()
        val usuarioPagamentoDAO = UsuarioPagamentoDAO.instance()

        requestData.valores.forEach {
            val usuario = usuarioDAO.obter(it.usuario.id)

            if (usuario == null) {
                violationException.addViolation("tipo.valores.usuario.id", "usuário ${it.usuario.id} inválido")
            } else {
                usuarioPagamentoDAO.inserirOuAtualizar(UsuarioPagamento(usuario, entidade, it.valor))
            }
        }
    }
}