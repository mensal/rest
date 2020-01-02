package rest.data

import core.entity.TipoDespesa
import java.util.*
import javax.validation.constraints.NotNull

class IdReqData : Requested<TipoDespesa> {

    @NotNull
    lateinit var id: UUID

    override fun escreverEm(entidade: TipoDespesa) {
        entidade.id = id
    }
}