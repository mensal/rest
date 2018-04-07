package rest.data

import core.entity.TipoDespesa
import java.util.*
import javax.validation.constraints.NotNull

class TipoDespesaReqData : ReqData<TipoDespesa> {

    @NotNull
    lateinit var id: UUID

    override fun escrever(entidade: TipoDespesa?): TipoDespesa? {
        entidade?.id = id
        return entidade
    }
}