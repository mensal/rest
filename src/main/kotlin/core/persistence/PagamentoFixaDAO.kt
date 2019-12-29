package core.persistence

import core.entity.PagamentoFixa
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.spi.CDI
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class PagamentoFixaDAO protected constructor() : PagamentoDAO<PagamentoFixa>() {

    companion object {
        fun instance() = CDI.current().select(PagamentoFixaDAO::class.java).get()!!
    }
}