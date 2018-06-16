package core.persistence

import core.entity.PagamentoDiversa
import javax.enterprise.inject.spi.CDI

open class PagamentoDiversaDAO protected constructor() : PagamentoDAO<PagamentoDiversa>() {

    companion object {
        fun instance() = CDI.current().select(PagamentoDiversaDAO::class.java).get()!!
    }
}