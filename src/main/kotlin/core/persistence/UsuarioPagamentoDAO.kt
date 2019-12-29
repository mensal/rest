package core.persistence

import core.entity.Pagamento
import core.entity.TipoDespesa
import core.entity.Usuario
import core.entity.UsuarioPagamento
import core.entity.UsuarioPagamento.UsuarioPagamentoPk
import javax.enterprise.context.Dependent
import javax.enterprise.inject.spi.CDI
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Dependent
@Transactional
open class UsuarioPagamentoDAO protected constructor() {

    @PersistenceContext
    protected lateinit var em: EntityManager

    open fun <T : TipoDespesa> obter(usuario: Usuario?, pagamento: Pagamento<T>?): UsuarioPagamento? = em.find(UsuarioPagamento::class.java, UsuarioPagamentoPk(usuario?.id, pagamento?.id))

    open fun <T : TipoDespesa> buscar(pagamento: Pagamento<T>): List<UsuarioPagamento>? {
        val jpql = " select up from UsuarioPagamento up where up.pagamento = :pagamento order by up.usuario.nome "
        val query = em.createQuery(jpql, UsuarioPagamento::class.java)
        query.setParameter("pagamento", pagamento)

        return query.resultList
    }

    open fun inserirOuAtualizar(entidade: UsuarioPagamento) {
        val persistido = obter(entidade.usuario, entidade.pagamento)

        if (persistido != null) {
            persistido.valor = entidade.valor
            em.merge(persistido)
        } else {
            em.persist(entidade)
        }
    }

    open fun <T : TipoDespesa> excluir(pagamento: Pagamento<T>) {
        val jpql = " delete from UsuarioPagamento up where up.pagamento = :pagamento "
        val query = em.createQuery(jpql)
        query.setParameter("pagamento", pagamento)

        query.executeUpdate()
    }

    companion object {
        fun instance() = CDI.current().select(UsuarioPagamentoDAO::class.java).get()!!
    }
}