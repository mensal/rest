package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.PagamentoDiversa
import java.time.LocalDate
import java.util.*

@JsonPropertyOrder("id", "data", "observacao", "tipo")
class PagamentoDiversaResData : ResData<PagamentoDiversa> {

    var id: UUID? = null

    var data: LocalDate? = null

    var observacao: String? = null

    lateinit var tipo: TipoDespesaDiversaResData

    override fun preencherCom(entidade: PagamentoDiversa?) {
        id = entidade?.id
        data = entidade?.data
        observacao = entidade?.observacao

        tipo = TipoDespesaDiversaResData()
        tipo.preencherCom(entidade?.tipo)
    }
}
