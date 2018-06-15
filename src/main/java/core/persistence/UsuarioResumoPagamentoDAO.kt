package core.persistence

import core.entity.Pagamento.Companion.primeiroDia
import core.entity.UsuarioResumoPagamento
import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import javax.enterprise.inject.spi.CDI
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Transactional
open class UsuarioResumoPagamentoDAO protected constructor() {

    @PersistenceContext
    private lateinit var em: EntityManager

    open fun pesquisar(ano: Int, mes: Int): List<UsuarioResumoPagamento> {

        val atual = atual(ano, mes)
        val corrente = corrente(ano, mes)

        val menor: BigDecimal = atual?.map { it.anterior }?.min() ?: ZERO
        val usuarios = UsuarioDAO.instance().pesquisar()

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
        jpql += "    and month(p.data) = :mes"
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

    companion object {
        fun instance() = CDI.current().select(UsuarioResumoPagamentoDAO::class.java).get()!!
    }
}