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

    @Inject
    open lateinit var em: EntityManager

    open fun obter(email: String): Usuario? {
        val ql = " select u from Usuario u where u.email = :email "
        val query = em.createQuery(ql, Usuario::class.java)
        query.setParameter("email", email)

        return try {
            query.singleResult
        } catch (cause: NoResultException) {
            null
        }
    }

    override fun pesquisar(params: Map<String, String>) = VersionadoCrudDAO.pesquisar(params, Usuario::class, em)
    override fun obter(id: UUID) = VersionadoCrudDAO.obter(id, Usuario::class, em)
    override fun inserir(entidade: Usuario) = VersionadoCrudDAO.inserir(entidade, em)
    override fun atualizar(entidade: Usuario) = VersionadoCrudDAO.atualizar(entidade, em)
    override fun excluir(entidade: Usuario) = VersionadoCrudDAO.excluir(entidade, em)
}