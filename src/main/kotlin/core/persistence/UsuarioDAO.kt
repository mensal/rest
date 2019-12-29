package core.persistence

import core.entity.Usuario
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.spi.CDI
import javax.persistence.NoResultException
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class UsuarioDAO protected constructor() : VersionadoCrudDAO<Usuario>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "nome asc"

    open fun obter(email: String): Usuario? {
        val jpql = " select e from ${entityClass.qualifiedName} e where e.email = :email "
        val query = em.createQuery(jpql, entityClass.java)
        query.setParameter("email", email)

        return try {
            query.singleResult
        } catch (cause: NoResultException) {
            null
        }
    }

    companion object {
        fun instance() = CDI.current().select(UsuarioDAO::class.java).get()!!
    }
}