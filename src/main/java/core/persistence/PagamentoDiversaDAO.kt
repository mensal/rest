package core.persistence

import core.entity.PagamentoDiversa
import javax.transaction.Transactional

@Transactional
open class PagamentoDiversaDAO : CrudDAO<PagamentoDiversa>() {

    override val entityClass = PagamentoDiversa::class.java
}