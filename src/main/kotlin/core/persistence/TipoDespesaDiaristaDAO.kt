package core.persistence

import core.entity.TipoDespesaDiarista
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
open class TipoDespesaDiaristaDAO : VersionadoCrudDAO<TipoDespesaDiarista> {

//    override fun pesquisarOrderBy(params: Map<String, String>) = "valor desc"

    @Inject
    open lateinit var em: EntityManager

    override fun pesquisar(params: Map<String, String>) = pesquisar(params, TipoDespesaDiarista::class, em)
    override fun obter(id: UUID) = obter(id, TipoDespesaDiarista::class, em)
    override fun inserir(entidade: TipoDespesaDiarista) = inserir(entidade, em)
    override fun atualizar(entidade: TipoDespesaDiarista) = atualizar(entidade, em)
    override fun excluir(entidade: TipoDespesaDiarista) = excluir(entidade, em)
}