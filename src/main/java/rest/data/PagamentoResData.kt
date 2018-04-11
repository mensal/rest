package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.Pagamento
import core.entity.TipoDespesa
import java.time.LocalDate
import java.util.*

@JsonPropertyOrder("id", "data", "tipo", "valores")
abstract class PagamentoResData<in E : Pagamento<T>, T : TipoDespesa, R : TipoDespesaResData<T>> : ResData<E> {

    var id: UUID? = null

    var data: LocalDate? = null

    var tipo: R? = null

    var valores: List<UsuarioPagamentoResData>? = null

    abstract fun novoTipoDespesaResponseData(): R

    override fun preencherCom(entidade: E?) {
        id = entidade?.id
        data = entidade?.data

        if (tipo == null) tipo = novoTipoDespesaResponseData()
        tipo?.preencherCom(entidade?.tipo)

        if (valores == null) valores = mutableListOf()
        valores = entidade?.valores?.map {
            val data = UsuarioPagamentoResData()
            data.preencherCom(it)
            data
        }
    }
}
