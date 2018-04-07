package rest.data

import core.entity.TipoDespesa
import java.util.*

class TipoDespesaResData : ResData<TipoDespesa, TipoDespesaResData> {

    var id: UUID? = null

    override fun ler(entidade: TipoDespesa?): TipoDespesaResData {
        id = entidade?.id
        return this
    }
}