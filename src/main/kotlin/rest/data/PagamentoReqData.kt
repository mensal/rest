package rest.data

import core.entity.Coordenada
import core.entity.Pagamento
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

abstract class PagamentoReqData<in E : Pagamento<*>> : RequestData<E> {

    @NotNull
    lateinit var data: LocalDate

    @Valid
    var coordenada: CoordenadaReqData? = null

    @Valid
    @NotNull
    lateinit var tipo: IdReqData

    @Valid
    @NotEmpty
    lateinit var valores: List<UsuarioPagamentoReqData>

    override fun escreverEm(entidade: E) {
        entidade.data = data

        if (entidade.coordenada == null) {
            entidade.coordenada = Coordenada()
        }

        coordenada?.escreverEm(entidade.coordenada!!)
    }
}
