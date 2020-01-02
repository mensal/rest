package core.persistence

import core.entity.Pagamento
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager
import javax.transaction.Transactional
import kotlin.reflect.KClass

@Transactional
@ApplicationScoped
interface PagamentoDAO<E : Pagamento<*>> : VersionadoCrudDAO<E> {

    companion object {
        fun <E : Pagamento<*>> pesquisar(params: Map<String, String>, type: KClass<E>, em: EntityManager, where: String = "", orderBy: String = "", prepare: PrepareQuery<E> = {}): List<E> {
            var condition = mutableListOf(where)
            params["ano"]?.let { condition.add("year(data) = :ano") }
            params["mes"]?.let { condition.add("month(data) = :mes") }

            val whereQl = condition.filter { it.isNotBlank() }.joinToString(" and ")
            val orderByQl = "${if (orderBy.isNotBlank()) "$orderBy, " else ""} data asc"

            return VersionadoCrudDAO.pesquisar(params, type, em, whereQl, orderByQl) { query ->
                prepare.invoke(query)
                params["ano"]?.let { query.setParameter("ano", it.toInt()) }
                params["mes"]?.let { query.setParameter("mes", it.toInt()) }
            }
        }

        fun <E : Pagamento<*>> obter(id: UUID, type: KClass<E>, em: EntityManager) = VersionadoCrudDAO.obter(id, type, em)
        fun <E : Pagamento<*>> inserir(entidade: E, em: EntityManager) = VersionadoCrudDAO.inserir(entidade, em)
        fun <E : Pagamento<*>> atualizar(entidade: E, em: EntityManager) = VersionadoCrudDAO.atualizar(entidade, em)
        fun <E : Pagamento<*>> excluir(entidade: E, em: EntityManager) = VersionadoCrudDAO.excluir(entidade, em)
    }
}