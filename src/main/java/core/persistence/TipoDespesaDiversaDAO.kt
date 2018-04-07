package core.persistence

import core.entity.TipoDespesaDiversa
import javax.transaction.Transactional

@Transactional
open class TipoDespesaDiversaDAO : CrudDAO<TipoDespesaDiversa>() {

    override val entityClass = TipoDespesaDiversa::class.java
}