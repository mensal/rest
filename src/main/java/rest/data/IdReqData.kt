package rest.data

import core.entity.TipoDespesa
import org.hibernate.validator.constraints.NotBlank
import java.util.*

class IdReqData : ReqData<TipoDespesa> {

    @NotBlank
    lateinit var id: UUID

    override fun escreverEm(entidade: TipoDespesa) {
        entidade.id = id
    }
}