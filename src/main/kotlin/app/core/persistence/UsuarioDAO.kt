package app.core.persistence

//import javax.enterprise.inject.spi.CDI
import app.core.entity.Usuario
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.NoResultException

@Component
class UsuarioDAO protected constructor() : VersionadoCrudDAO<Usuario>() {

    override fun pesquisarOrderBy(params: Map<String, String>) = "nome asc"

    @Transactional
    fun obter(email: String): Usuario? {
        val jpql = " select e from ${entityClass.qualifiedName} e where e.email = :email "
        val query = em.createQuery(jpql, entityClass.java)
        query.setParameter("email", email)

        return try {
            query.singleResult
        } catch (cause: NoResultException) {
            null
        }
    }

//    companion object {
//        fun instance() = autowired(UsuarioDAO::class)
//    }
}