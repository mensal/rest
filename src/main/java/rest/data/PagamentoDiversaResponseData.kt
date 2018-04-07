package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.PagamentoDiversa
import java.util.*

@JsonPropertyOrder("id", "data", "observacao")
class PagamentoDiversaResponseData : ResponseData<PagamentoDiversa, PagamentoDiversaResponseData> {

    var id: UUID? = null

    var data: Date? = null

    var observacao: String? = null

    override fun ler(periodo: PagamentoDiversa?): PagamentoDiversaResponseData {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
