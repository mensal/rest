package core.persistence

import core.entity.Versionado
import core.persistence.CrudDAO.Companion.pesquisar
import java.time.ZonedDateTime
import java.util.*
import javax.persistence.EntityManager
import kotlin.reflect.KClass

interface VersionadoCrudDAO<V : Versionado> : CrudDAO<V> {
    companion object {
        fun <E : Versionado> pesquisar(params: Map<String, String>, type: KClass<E>, em: EntityManager, where: String = "", orderBy: String = "", prepare: PrepareQuery<E> = {}): List<E> {
            var condition = mutableListOf(where)
            params["atualizado_apos"]?.let { condition.add(" atualizadoEm > :atualizadoApos ") }
            params["mostrar_excluidos"]?.toBoolean().let { if (it == null || !it) condition.add(" excluidoEm is null ") }

            val whereQl = condition.filter { it.isNotBlank() }.joinToString(" and ")
            val orderByQl = "${if (orderBy.isNotBlank()) "$orderBy, " else ""} atualizadoEm asc"

            return pesquisar(type, em, whereQl, orderByQl) { query ->
                prepare.invoke(query)
                params["atualizado_apos"]?.let { query.setParameter("atualizadoApos", ZonedDateTime.parse(it)) }
            }
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