package core.persistence

import core.entity.PagamentoDiversa
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class PagamentoDiversaDAO protected constructor() : PagamentoDAO<PagamentoDiversa>() {

//    companion object {
//        fun instance() = CDI.current().select(PagamentoDiversaDAO::class.java).get()!!
//    }
}