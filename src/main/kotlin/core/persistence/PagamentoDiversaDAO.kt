package core.persistence

import core.entity.PagamentoDiversa
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class PagamentoDiversaDAO : PagamentoDAO<PagamentoDiversa>()