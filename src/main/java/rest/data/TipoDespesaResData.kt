package rest.data

import core.entity.TipoDespesa
import java.util.*

class TipoDespesaResData : ResData<TipoDespesa> {

    var id: UUID? = null

    override fun preencherCom(entidade: TipoDespesa?) {
        id = entidade?.id
    }
}