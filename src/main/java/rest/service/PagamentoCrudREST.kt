package rest.service

import core.entity.Pagamento
import core.entity.UsuarioPagamento
import core.persistence.CrudDAO
import core.persistence.UsuarioDAO
import core.persistence.UsuarioPagamentoDAO
import rest.data.PagamentoReqData
import rest.data.ResData

abstract class PagamentoCrudREST<E : Pagamento<*>, Q : PagamentoReqData<E>, out S : ResData<E>, A : CrudDAO<E>> : CrudREST<E, Q, S, A>() {

    override fun depoisDePesquisar(entidade: E): E {
        entidade.valores = UsuarioPagamentoDAO.instance().buscar(entidade)
        return entidade
    }

    override fun depoisDePesquisar(entidades: List<E>) = entidades.sortedBy { it.data }

    override fun antesDeExcluir(entidade: E) {
        UsuarioPagamentoDAO.instance().excluir(entidade)
    }

//    override fun antesDePersistir(entidade: E, requestData: Q) {
//        val tipo = TipoDespesaDiversaDAO.instance().obter(requestData.tipo.id)
//        if (tipo != null) entidade.tipo = tipo else violationException.addViolation("tipo.id", "tipo de despesa inválido")
//    }

    override fun depoisDePersistir(entidade: E, requestData: Q) {
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