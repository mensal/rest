package core.persistence

import core.entity.Usuario
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.persistence.NoResultException
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class UsuarioDAO : VersionadoCrudDAO<Usuario> {

//    override fun pesquisarOrderBy(params: Map<String, String>) = "nome asc"
//
//    open fun obter(email: String): Usuario? {
//        val jpql = " select e from Usuario e where e.email = :email "
//        val query = em.createQuery(jpql, entityClass.java)
//        query.setParameter("email", email)
//
//        return try {
//            query.singleResult
//        } catch (cause: NoResultException) {
//            null
//        }
//    }

    @Inject
    open lateinit var em2: EntityManager

    override fun pesquisar(params: Map<String, String>) = VersionadoCrudDAO.pesquisar2(params, Usuario::class, em2)

    override fun obter(id: UUID) = VersionadoCrudDAO.obter2(id, Usuario::class, em2)

    open fun obter(email: String): Usuario? {
        val ql = " select u from Usuario u where u.email = :email "
        val query = em2.createQuery(ql, Usuario::class.java)
        query.setParameter("email", email)

        return try {
            query.singleResult
        } catch (cause: NoResultException) {
            null
        }
    }

    override fun inserir(entidade: Usuario) = VersionadoCrudDAO.inserir2(entidade, em2)

    override fun atualizar(entidade: Usuario) = VersionadoCrudDAO.atualizar2(entidade, em2)

    override fun excluir(entidade: Usuario) = VersionadoCrudDAO.excluir2(entidade, em2)
}