package rest.data

import core.entity.PagamentoDiversa
import core.entity.TipoDespesaDiversa
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotNull

class PagamentoDiversaReqData : ReqData<PagamentoDiversa> {

    @Valid
    @NotNull
    lateinit var tipo: TipoDespesaReqData

    @NotNull
    lateinit var data: LocalDate

    var observacao: String? = null

    override fun escreverEm(entidade: PagamentoDiversa) {
        if (entidade.tipo == null) entidade.tipo = TipoDespesaDiversa()
        tipo.escreverEm(entidade.tipo!!)

        entidade.data = data
        entidade.observacao = observacao
    }
}
