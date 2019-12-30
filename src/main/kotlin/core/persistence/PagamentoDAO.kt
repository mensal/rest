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
        fun <E : Pagamento<*>> pesquisar2(params: Map<String, String> = emptyMap(), type: KClass<E>, em: EntityManager): List<E> {
            var ql = StringBuffer()

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

        fun <E : Pagamento<*>> obter2(id: UUID, type: KClass<E>, em: EntityManager): E? = em.find(type.java, id)

        fun <E : Pagamento<*>> inserir2(entidade: E, em: EntityManager) = em.persist(entidade)

        fun <E : Pagamento<*>> atualizar2(entidade: E, em: EntityManager) = em.merge(entidade)

        fun <E : Pagamento<*>> excluir2(entidade: E, em: EntityManager) {
            entidade.excluidoEm = ZonedDateTime.now()
            entidade.atualizadoEm = entidade.excluidoEm

            VersionadoCrudDAO.atualizar2(entidade, em)
        }
    }

//    override fun pesquisarWhere(params: Map<String, String>): String {
//        var criterios = mutableListOf<String>()
//        super.pesquisarWhere(params).let { if (it.isNotBlank()) criterios.add(it) }
//
//        if (params.containsKey("ano")) {
//            criterios.add("year(data) = :ano")
//        }
//
//        if (params.containsKey("mes")) {
//            criterios.add("month(data) = :mes")
//        }
//
//        return criterios.let { if (it.isEmpty()) "" else criterios.joinToString(" and ") }
//    }
//
//    override fun antesDePesquisar(params: Map<String, String>, query: TypedQuery<E>) {
//        super.antesDePesquisar(params, query)
//
//        if (params.containsKey("ano")) {
//            query.setParameter("ano", params["ano"]!!.toInt())
//        }
//
//        if (params.containsKey("mes")) {
//            query.setParameter("mes", params["mes"]!!.toInt())
//        }
//    }
//
//    override fun pesquisarOrderBy(params: Map<String, String>) = "data asc, atualizadoEm asc"
}