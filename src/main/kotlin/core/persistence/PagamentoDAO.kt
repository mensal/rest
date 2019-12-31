package core.persistence

import core.entity.Pagamento
import java.time.ZonedDateTime
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager
import javax.transaction.Transactional
import kotlin.reflect.KClass

@Transactional
@ApplicationScoped
interface PagamentoDAO<E : Pagamento<*>> : VersionadoCrudDAO<E> {

    companion object {
        fun <E : Pagamento<*>> pesquisar(params: Map<String, String> = emptyMap(), type: KClass<E>, em: EntityManager): List<E> {
            val ql = StringBuffer()

            ql.append(" select e from ${type.java.canonicalName} e where 1 = 1 ")

            params["atualizado_apos"]?.let { ql.append(" and e.atualizadoEm > :atualizadoApos ") }
            params["mostrar_excluidos"]?.let { if (!it.toBoolean()) ql.append(" and e.excluidoEm is null ") }

            params["ano"]?.let { ql.append(" and year(e.data) = :ano ") }
            params["mes"]?.let { ql.append(" and month(e.data) = :mes ") }

            ql.append(" order by e.data asc, e.atualizadoEm asc ")

            val query = em.createQuery(ql.toString(), type.java)

            params["atualizado_apos"]?.let { query.setParameter("atualizadoApos", ZonedDateTime.parse(it)) }
            params["ano"]?.let { query.setParameter("ano", it.toInt()) }
            params["mes"]?.let { query.setParameter("mes", it.toInt()) }

            return query.resultList
        }

        fun <E : Pagamento<*>> obter(id: UUID, type: KClass<E>, em: EntityManager) = VersionadoCrudDAO.obter(id, type, em)
        fun <E : Pagamento<*>> inserir(entidade: E, em: EntityManager) = VersionadoCrudDAO.inserir(entidade, em)
        fun <E : Pagamento<*>> atualizar(entidade: E, em: EntityManager) = VersionadoCrudDAO.atualizar(entidade, em)
        fun <E : Pagamento<*>> excluir(entidade: E, em: EntityManager) = VersionadoCrudDAO.excluir(entidade, em)
    }
}