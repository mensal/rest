package rest.data

import core.entity.PagamentoDiversa
import java.util.*
import javax.validation.constraints.NotNull

class PagamentoDiversaRequestData : RequestData<PagamentoDiversa> {

    @NotNull
    lateinit var data: Date

    var observacao: String? = null

    override fun escrever(periodo: PagamentoDiversa?): PagamentoDiversa? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
