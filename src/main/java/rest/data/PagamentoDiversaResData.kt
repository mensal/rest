package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.PagamentoDiversa
import java.time.LocalDate
import java.util.*

@JsonPropertyOrder("id", "data", "observacao")
class PagamentoDiversaResData : ResData<PagamentoDiversa> {

    var id: UUID? = null

    var data: LocalDate? = null

    var observacao: String? = null

    override fun preencherCom(entidade: PagamentoDiversa?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
