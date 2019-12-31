package core.persistence

import core.entity.TipoDespesaDiversa
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
open class TipoDespesaDiversaDAO : VersionadoCrudDAO<TipoDespesaDiversa> {

//    override fun pesquisarOrderBy(params: Map<String, String>) = "nome asc"

    @Inject
    open lateinit var em: EntityManager

    override fun pesquisar(params: Map<String, String>) = pesquisar(params, TipoDespesaDiversa::class, em, "", "nome asc")
    override fun obter(id: UUID) = obter(id, TipoDespesaDiversa::class, em)
    override fun inserir(entidade: TipoDespesaDiversa) = inserir(entidade, em)
    override fun atualizar(entidade: TipoDespesaDiversa) = atualizar(entidade, em)
    override fun excluir(entidade: TipoDespesaDiversa) = excluir(entidade, em)
}