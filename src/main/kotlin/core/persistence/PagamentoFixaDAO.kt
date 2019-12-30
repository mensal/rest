package core.persistence

import core.entity.PagamentoFixa
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class PagamentoFixaDAO : PagamentoDAO<PagamentoFixa>()