package core.persistence

import core.entity.TipoDespesaFixa
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class TipoDespesaFixaDAO : VersionadoCrudDAO<TipoDespesaFixa> {

//    override fun pesquisarOrderBy(params: Map<String, String>) = "vencimento asc, nome asc"

    @Inject
    open lateinit var em2: EntityManager

    override fun pesquisar(params: Map<String, String>) = VersionadoCrudDAO.pesquisar2(params, TipoDespesaFixa::class, em2)

    override fun obter(id: UUID) = VersionadoCrudDAO.obter2(id, TipoDespesaFixa::class, em2)

    override fun inserir(entidade: TipoDespesaFixa) = VersionadoCrudDAO.inserir2(entidade, em2)

    override fun atualizar(entidade: TipoDespesaFixa) = VersionadoCrudDAO.atualizar2(entidade, em2)

    override fun excluir(entidade: TipoDespesaFixa) = VersionadoCrudDAO.excluir2(entidade, em2)
}