package rest.data

import core.entity.PagamentoDiversa
import org.hibernate.validator.constraints.NotEmpty
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotNull

class PagamentoDiversaReqData : ReqData<PagamentoDiversa> {

    @NotNull
    lateinit var data: LocalDate

    var observacao: String? = null

    @Valid
    @NotNull
    lateinit var tipo: IdReqData

    @Valid
    @NotEmpty
    lateinit var valores: List<UsuarioPagamentoReqData>

    override fun escreverEm(entidade: PagamentoDiversa) {
        entidade.data = data
        entidade.observacao = observacao
    }
}
