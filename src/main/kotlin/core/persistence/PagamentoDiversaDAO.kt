package core.persistence

import core.entity.PagamentoDiversa
import javax.enterprise.context.Dependent
import javax.enterprise.inject.spi.CDI

@Dependent
open class PagamentoDiversaDAO protected constructor() : PagamentoDAO<PagamentoDiversa>() {

    companion object {
        fun instance() = CDI.current().select(PagamentoDiversaDAO::class.java).get()!!
    }
}