package app.rest.service

import app.core.entity.Pagamento
import app.core.entity.TipoDespesa
import app.core.entity.UsuarioPagamento
import app.core.persistence.CrudDAO
import app.core.persistence.UsuarioDAO
import app.core.persistence.UsuarioPagamentoDAO
import app.core.util.Reflections
import app.core.util.autowired
import app.rest.ClientViolationException
import app.rest.data.PagamentoReqData
import app.rest.data.ResData
import java.time.ZonedDateTime

abstract class PagamentoREST<ENT : Pagamento<T>, T : TipoDespesa, REQ : PagamentoReqData<ENT>, RES : ResData<ENT>, DAO : CrudDAO<ENT>, out TDAO : CrudDAO<T>> : CrudREST<ENT, REQ, RES, DAO>() {

    protected open val tipoDAO: TDAO
        get() = autowired(Reflections.argument(this, PagamentoREST::class, 5))

    override fun depoisDePesquisar(entidade: ENT): ENT {
        entidade.valores = autowired(UsuarioPagamentoDAO::class).buscar(entidade)
        return entidade
    }

    override fun antesDeExcluir(entidade: ENT) {
        autowired(UsuarioPagamentoDAO::class).excluir(entidade)
    }

    override fun antesDePersistir(entidade: ENT, requestData: REQ) {
        val tipo = tipoDAO.obter(requestData.tipo.id)
        if (tipo != null) entidade.tipo = tipo else violationException.addViolation("tipo.id", "tipo de despesa inválido")
    }

    override fun depoisDePersistir(entidade: ENT, requestData: REQ) {
        val usuarioDAO = autowired(UsuarioDAO::class)
        val usuarioPagamentoDAO = autowired(UsuarioPagamentoDAO::class)
        usuarioPagamentoDAO.excluir(entidade)

        requestData.valores.forEach {
            val usuario = usuarioDAO.obter(it.usuario.id)

            if (usuario == null) {
                violationException.addViolation("tipo.valores.usuario.id", "usuário ${it.usuario.id} inválido")
            } else {
                usuarioPagamentoDAO.inserirOuAtualizar(UsuarioPagamento(usuario, entidade, it.valor))
            }
        }

        entidade.atualizadoEm = ZonedDateTime.now()
        dao.atualizar(entidade)

        entidade.valores = usuarioPagamentoDAO.buscar(entidade)
    }

    override fun validaParametrosDePesquisa(params: Map<String, String>, exception: ClientViolationException) {
        mapOf("mes" to params["mes"], "ano" to params["ano"]).forEach {
            if (it.value != null) {
                if (it.value!!.toIntOrNull() == null) exception.addViolation(it.key, "informe um número")
            }
        }
    }
}