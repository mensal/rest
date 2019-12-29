package core.persistence

import core.entity.PagamentoCombustivel
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.spi.CDI
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class PagamentoCombustivelDAO protected constructor() : PagamentoDAO<PagamentoCombustivel>() {

    companion object {
        fun instance() = CDI.current().select(PagamentoCombustivelDAO::class.java).get()!!
    }
}