package rest.data

import core.entity.PagamentoDiversa
import core.entity.TipoDespesaDiversa
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
    @NotNull
    @NotEmpty
//    @Size(min = 1)
    lateinit var valores: List<PagamentoValorReqData>

    override fun escreverEm(entidade: PagamentoDiversa) {
        if (entidade.tipo == null) entidade.tipo = TipoDespesaDiversa()
        tipo.escreverEm(entidade.tipo!!)

        entidade.data = data
        entidade.observacao = observacao
    }
}
