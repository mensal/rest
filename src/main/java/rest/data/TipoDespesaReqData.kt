package rest.data

import core.entity.TipoDespesa
import java.util.*
import javax.validation.constraints.NotNull

class TipoDespesaReqData : ReqData<TipoDespesa> {

    @NotNull
    lateinit var id: UUID

    override fun escreverEm(entidade: TipoDespesa) {
        entidade.id = id
    }
}