package rest.data

import core.entity.PagamentoDiversa
import java.time.LocalDate
import javax.validation.constraints.NotNull

class PagamentoDiversaReqData : ReqData<PagamentoDiversa> {

    @NotNull
    lateinit var data: LocalDate

    var observacao: String? = null

    override fun escrever(entidade: PagamentoDiversa?): PagamentoDiversa? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
