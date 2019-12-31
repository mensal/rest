package core.persistence

import core.entity.TipoDespesaFixa
import core.persistence.VersionadoCrudDAO.Companion.atualizar
import core.persistence.VersionadoCrudDAO.Companion.excluir
import core.persistence.VersionadoCrudDAO.Companion.inserir
import core.persistence.VersionadoCrudDAO.Companion.obter
import core.persistence.VersionadoCrudDAO.Companion.pesquisar
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
    open lateinit var em: EntityManager

    override fun pesquisar(params: Map<String, String>) = pesquisar(params, TipoDespesaFixa::class, em, "", "vencimento asc, nome asc")
    override fun obter(id: UUID) = obter(id, TipoDespesaFixa::class, em)
    override fun inserir(entidade: TipoDespesaFixa) = inserir(entidade, em)
    override fun atualizar(entidade: TipoDespesaFixa) = atualizar(entidade, em)
    override fun excluir(entidade: TipoDespesaFixa) = excluir(entidade, em)
}