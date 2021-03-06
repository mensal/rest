package core.persistence

import core.entity.PagamentoCombustivel
import javax.enterprise.inject.spi.CDI

open class PagamentoCombustivelDAO protected constructor() : PagamentoDAO<PagamentoCombustivel>() {

    companion object {
        fun instance() = CDI.current().select(PagamentoCombustivelDAO::class.java).get()!!
    }
}