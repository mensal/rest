package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.PagamentoDiversa
import java.time.LocalDate
import java.util.*

@JsonPropertyOrder("id", "data", "observacao")
class PagamentoDiversaResData : ResData<PagamentoDiversa, PagamentoDiversaResData> {

    var id: UUID? = null

    var data: LocalDate? = null

    var observacao: String? = null

    override fun ler(entidade: PagamentoDiversa?): PagamentoDiversaResData {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
