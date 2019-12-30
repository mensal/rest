package core.persistence

import core.entity.TipoDespesaDiarista
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
    open lateinit var em2: EntityManager

    override fun pesquisar(params: Map<String, String>) = VersionadoCrudDAO.pesquisar2(params, TipoDespesaDiarista::class, em2)

    override fun obter(id: UUID) = VersionadoCrudDAO.obter2(id, TipoDespesaDiarista::class, em2)

    override fun inserir(entidade: TipoDespesaDiarista) = VersionadoCrudDAO.inserir2(entidade, em2)

    override fun atualizar(entidade: TipoDespesaDiarista) = VersionadoCrudDAO.atualizar2(entidade, em2)

    override fun excluir(entidade: TipoDespesaDiarista) = VersionadoCrudDAO.excluir2(entidade, em2)
}