package app.core.persistence

import app.core.entity.PagamentoDiversa
import org.springframework.stereotype.Component

//import javax.enterprise.inject.spi.CDI

@Component
class PagamentoDiversaDAO protected constructor() : PagamentoDAO<PagamentoDiversa>() {

//    companion object {
//        fun instance() = CDI.current().select(PagamentoDiversaDAO::class.java).get()!!
//    }
}