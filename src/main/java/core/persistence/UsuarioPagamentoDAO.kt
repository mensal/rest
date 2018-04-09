package core.persistence

import core.entity.Pagamento
import core.entity.Usuario
import core.entity.UsuarioPagamento
import core.entity.UsuarioPagamento.UsuarioPagamentoPk
import javax.enterprise.inject.spi.CDI
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Transactional(rollbackOn = [Throwable::class])
open class UsuarioPagamentoDAO protected constructor() {

    @PersistenceContext
    private lateinit var em: EntityManager

    open fun obter(usuario: Usuario?, pagamento: Pagamento?): UsuarioPagamento? = em.find(UsuarioPagamento::class.java, UsuarioPagamentoPk(usuario?.id, pagamento?.id))

    open fun buscar(pagamento: Pagamento): List<UsuarioPagamento>? {
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

    companion object {
        fun instance() = CDI.current().select(UsuarioPagamentoDAO::class.java).get()!!
    }
}