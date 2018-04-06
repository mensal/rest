package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.PagamentoDiversa
import java.util.*

@JsonPropertyOrder("id", "data", "observacao")
class PagamentoDiversaData : Data<PagamentoDiversa> {

    var id: UUID? = null

    var data: Date? = null

    var observacao: String? = null

    override fun ler(periodo: PagamentoDiversa?): Data<PagamentoDiversa> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun escrever(periodo: PagamentoDiversa?): PagamentoDiversa? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
