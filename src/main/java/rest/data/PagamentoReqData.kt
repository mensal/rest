package rest.data

import core.entity.Pagamento
import org.hibernate.validator.constraints.NotEmpty
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotNull

abstract class PagamentoReqData<E : Pagamento<*>> : ReqData<E> {

    @NotNull
    lateinit var data: LocalDate

    @Valid
    @NotNull
    lateinit var tipo: IdReqData

    @Valid
    @NotEmpty
    lateinit var valores: List<UsuarioPagamentoReqData>

    override fun escreverEm(entidade: E) {
        entidade.data = data
    }
}
