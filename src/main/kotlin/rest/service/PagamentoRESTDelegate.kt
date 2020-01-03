package rest.service

import core.entity.Pagamento
import core.entity.TipoDespesa
import core.entity.UsuarioPagamento
import core.persistence.CrudDAO
import core.persistence.UsuarioDAO
import core.persistence.UsuarioPagamentoDAO
import rest.ClientViolationException.Violation
import rest.data.PagamentoReqData
import java.time.ZonedDateTime
import javax.inject.Inject

abstract class PagamentoRESTDelegate<P : Pagamento<T>, I : PagamentoReqData<P>, T : TipoDespesa, DP : CrudDAO<P>, DT : CrudDAO<T>> : CrudRESTDelegate<P, I>  /*<ENT : Pagamento<T>, T : TipoDespesa, REQ : PagamentoReqData<ENT>, RES : ResData<ENT>, DAO : CrudDAO<ENT>, out TDAO : CrudDAO<T>> : CrudREST<ENT>()*/ {

    @Inject
    lateinit var usuarioDAO: UsuarioDAO

    @Inject
    lateinit var usuarioPagamentoDAO: UsuarioPagamentoDAO

    abstract var dao: DP
    abstract var tipoDAO: DT

    override fun depoisDePesquisar(entidade: P) {
        entidade.valores = usuarioPagamentoDAO.buscar(entidade)
    }

    override fun antesDePersistir(entidade: P, requestData: I) {
        val tipo = tipoDAO.obter(requestData.tipo.id)
        if (tipo != null) entidade.tipo = tipo else add(Violation("tipo.id", "tipo de despesa inválido"))
    }

    override fun depoisDePersistir(entidade: P, requestData: I) {
        usuarioPagamentoDAO.excluir(entidade)

        requestData.valores.forEach {
            val usuario = usuarioDAO.obter(it.usuario.id)

            if (usuario == null) {
                add(Violation("tipo.valores.usuario.id", "usuário ${it.usuario.id} inválido"))
            } else {
                val entidadeP = dao.obter(entidade.id!!)
                usuarioPagamentoDAO.inserirOuAtualizar(UsuarioPagamento(usuario, entidadeP, it.valor))
            }
        }

        entidade.atualizadoEm = ZonedDateTime.now()
        dao.atualizar(entidade)

        entidade.valores = usuarioPagamentoDAO.buscar(entidade)
    }

    override fun antesDeExcluir(entidade: P) {
        usuarioPagamentoDAO.excluir(entidade)
    }
}