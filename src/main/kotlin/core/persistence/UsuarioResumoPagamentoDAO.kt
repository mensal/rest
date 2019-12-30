package core.persistence

import core.entity.Pagamento.Companion.primeiroDia
import core.entity.UsuarioResumoPagamento
import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class UsuarioResumoPagamentoDAO {

    @Inject
    open lateinit var em: EntityManager

    @Inject
    open lateinit var usuarioDAO: UsuarioDAO

    open fun pesquisar(ano: Int, mes: Int): List<UsuarioResumoPagamento> {
        val atual = atual(ano, mes)
        val corrente = corrente(ano, mes)

        val menor: BigDecimal = atual?.map { it.anterior }?.min() ?: ZERO
        val usuarios = usuarioDAO.pesquisar2()

        return usuarios.map { u ->
            UsuarioResumoPagamento(u,
                    corrente?.firstOrNull { u.id == it.usuario.id }?.atual ?: ZERO,
                    (atual?.firstOrNull { u.id == it.usuario.id }?.atual ?: ZERO) - menor)
        }
    }

    protected open fun atual(ano: Int, mes: Int): List<UsuarioResumoPagamento>? {
        var jpql = ""

        jpql += " select "
        jpql += "    new ${UsuarioResumoPagamento::class.qualifiedName} ( "
        jpql += "        up.usuario, "
        jpql += "        sum(up.valor), "
        jpql += "        sum(up.valor) "
        jpql += "        )"
        jpql += "   from UsuarioPagamento up "
        jpql += "   join up.pagamento p "
        jpql += "  where p.data < :data "
        jpql += "    and p.excluidoEm is null "
        jpql += "  group by "
        jpql += "        up.usuario, "
        jpql += "        up.usuario.nome "
        jpql += "  order by "
        jpql += "        up.usuario.nome "

        val query = em.createQuery(jpql, UsuarioResumoPagamento::class.java)
        query.setParameter("data", primeiroDia(ano, mes))

        return query.resultList
    }

    protected open fun corrente(ano: Int, mes: Int): List<UsuarioResumoPagamento>? {
        var jpql = ""

        jpql += " select "
        jpql += "    new ${UsuarioResumoPagamento::class.qualifiedName} ( "
        jpql += "        up.usuario, "
        jpql += "        sum(up.valor), "
        jpql += "        sum(up.valor) "
        jpql += "        )"
        jpql += "   from UsuarioPagamento up "
        jpql += "   join up.pagamento p "
        jpql += "  where year(p.data) = :ano "
        jpql += "    and month(p.data) = :mes "
        jpql += "    and p.excluidoEm is null "
        jpql += "  group by "
        jpql += "        up.usuario, "
        jpql += "        up.usuario.nome "
        jpql += "  order by "
        jpql += "        up.usuario.nome "

        val query = em.createQuery(jpql, UsuarioResumoPagamento::class.java)
        query.setParameter("ano", ano)
        query.setParameter("mes", mes)

        return query.resultList
    }
}