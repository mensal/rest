package app.core.persistence

import app.core.entity.PagamentoFixa
import org.springframework.stereotype.Component

@Component
class PagamentoFixaDAO protected constructor() : PagamentoDAO<PagamentoFixa>() {

//    companion object {
//        fun instance() = CDI.current().select(PagamentoFixaDAO::class.java).get()!!
//    }
}