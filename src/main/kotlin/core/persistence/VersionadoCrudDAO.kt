package core.persistence

import core.entity.Versionado
import java.time.ZonedDateTime
import java.util.*
import javax.persistence.EntityManager
import kotlin.reflect.KClass

interface VersionadoCrudDAO<V : Versionado> : CrudDAO<V> {
    companion object {
        fun <E : Versionado> pesquisar(params: Map<String, String> = emptyMap(), type: KClass<E>, em: EntityManager): List<E> {
            val ql = StringBuffer()

            ql.append(" select e from ${type.java.canonicalName} e where 1 = 1 ")

            params["atualizado_apos"]?.let { ql.append(" and e.atualizadoEm > :atualizadoApos ") }
            params["mostrar_excluidos"]?.let { if (!it.toBoolean()) ql.append(" and e.excluidoEm is null ") }

            ql.append(" order by e.atualizadoEm asc ")

            val query = em.createQuery(ql.toString(), type.java)

            params["atualizado_apos"]?.let { query.setParameter("atualizadoApos", ZonedDateTime.parse(it)) }

            return query.resultList
        }

        fun <E : Versionado> obter(id: UUID, type: KClass<E>, em: EntityManager) = CrudDAO.obter(id, type, em)
        fun <E : Versionado> inserir(entidade: E, em: EntityManager) = CrudDAO.inserir(entidade, em)
        fun <E : Versionado> atualizar(entidade: E, em: EntityManager) = CrudDAO.atualizar(entidade, em)

        fun <E : Versionado> excluir(entidade: E, em: EntityManager) {
            entidade.excluidoEm = ZonedDateTime.now()
            entidade.atualizadoEm = entidade.excluidoEm

            atualizar(entidade, em)
        }
    }
}