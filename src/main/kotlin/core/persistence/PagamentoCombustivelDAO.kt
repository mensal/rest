package core.persistence

import core.entity.PagamentoCombustivel
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
open class PagamentoCombustivelDAO : PagamentoDAO<PagamentoCombustivel>()