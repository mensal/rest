package rest.data

import core.entity.Coordenada
import java.math.BigDecimal
import javax.validation.constraints.NotNull

class CoordenadaReqData : Requested<Coordenada> {

    @NotNull
    lateinit var latitude: BigDecimal

    @NotNull
    lateinit var longitude: BigDecimal

    override fun escreverEm(entidade: Coordenada) {
        entidade.latitude = latitude
        entidade.longitude = longitude
    }
}