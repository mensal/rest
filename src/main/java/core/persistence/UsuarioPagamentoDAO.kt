package core.persistence

import core.entity.UsuarioPagamento
import javax.enterprise.inject.spi.CDI
import javax.transaction.Transactional

@Transactional(rollbackOn = [Throwable::class])
open class UsuarioPagamentoDAO protected constructor() {

    open fun inserirOuAtualizar(entidade: UsuarioPagamento) {


    }

    companion object {
        fun instance() = CDI.current().select(UsuarioPagamentoDAO::class.java).get()!!
    }
}