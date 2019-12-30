package core.persistence

import java.util.*

interface CrudDAO<E : Any> {

//    @Inject
//    open lateinit var em: EntityManager
//
//    protected open val entityClass: KClass<E>
//        get() = Reflections.argument(this, CrudDAO::class, 0)
//
//    protected open fun pesquisarWhere(params: Map<String, String>): String = ""
//
//    protected open fun pesquisarOrderBy(params: Map<String, String>): String = ""
//
//    protected open fun antesDePesquisar(params: Map<String, String>, query: TypedQuery<E>) {}

    fun obter(id: UUID): E?

    fun pesquisar(params: Map<String, String> = emptyMap()): List<E>

    fun inserir(entidade: E)

    fun atualizar(entidade: E): E

    fun excluir(entidade: E)
}