package rest.service

import core.entity.Pagamento
import core.entity.TipoDespesa
import core.entity.UsuarioPagamento
import core.persistence.CrudDAO
import core.persistence.UsuarioDAO
import core.persistence.UsuarioPagamentoDAO
import rest.data.PagamentoReqData
import rest.data.ResData

abstract class PagamentoREST<ENT : Pagamento<T>, T : TipoDespesa, REQ : PagamentoReqData<ENT>, out RES : ResData<ENT>, DAO : CrudDAO<ENT>, TDAO : CrudDAO<T>> : CrudREST<ENT, REQ, RES, DAO>() {

    protected abstract var tipoDAO: TDAO

    override fun depoisDePesquisar(entidade: ENT): ENT {
        entidade.valores = UsuarioPagamentoDAO.instance().buscar(entidade)
        return entidade
    }

    override fun antesDeExcluir(entidade: ENT) {
        UsuarioPagamentoDAO.instance().excluir(entidade)
    }

    override fun antesDePersistir(entidade: ENT, requestData: REQ) {
        val tipo = tipoDAO.obter(requestData.tipo.id)
        if (tipo != null) entidade.tipo = tipo else violationException.addViolation("tipo.id", "tipo de despesa inválido")
    }

    override fun depoisDePersistir(entidade: ENT, requestData: REQ) {
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

        entidade.valores = usuarioPagamentoDAO.buscar(entidade)
    }
}