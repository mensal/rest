package app.core.persistence

//import javax.enterprise.inject.spi.CDI
import app.core.entity.Pagamento
import app.core.entity.TipoDespesa
import app.core.entity.Usuario
import app.core.entity.UsuarioPagamento
import app.core.entity.UsuarioPagamento.UsuarioPagamentoPk
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
@Transactional
class UsuarioPagamentoDAO protected constructor() {

    @PersistenceContext
    private lateinit var em: EntityManager

    fun <T : TipoDespesa> obter(usuario: Usuario?, pagamento: Pagamento<T>?): UsuarioPagamento? = em.find(UsuarioPagamento::class.java, UsuarioPagamentoPk(usuario?.id, pagamento?.id))

    fun <T : TipoDespesa> buscar(pagamento: Pagamento<T>): List<UsuarioPagamento>? {
        val jpql = " select up from UsuarioPagamento up where up.pagamento = :pagamento order by up.usuario.nome "
        val query = em.createQuery(jpql, UsuarioPagamento::class.java)
        query.setParameter("pagamento", pagamento)

        return query.resultList
    }

    fun inserirOuAtualizar(entidade: UsuarioPagamento) {
        val persistido = obter(entidade.usuario, entidade.pagamento)

        if (persistido != null) {
            persistido.valor = entidade.valor
            em.merge(persistido)
        } else {
            em.persist(entidade)
        }
    }

    fun <T : TipoDespesa> excluir(pagamento: Pagamento<T>) {
        val jpql = " delete from UsuarioPagamento up where up.pagamento = :pagamento "
        val query = em.createQuery(jpql)
        query.setParameter("pagamento", pagamento)

        query.executeUpdate()
    }

//    companion object {
//        fun instance() = CDI.current().select(UsuarioPagamentoDAO::class.java).get()!!
//    }
}