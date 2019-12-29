package core.persistence

import core.entity.PagamentoFixa
import javax.enterprise.context.Dependent
import javax.enterprise.inject.spi.CDI

@Dependent
open class PagamentoFixaDAO protected constructor() : PagamentoDAO<PagamentoFixa>() {

    companion object {
        fun instance() = CDI.current().select(PagamentoFixaDAO::class.java).get()!!
    }
}