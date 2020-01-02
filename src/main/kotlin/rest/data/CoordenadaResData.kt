package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.Coordenada
import java.math.BigDecimal

@JsonPropertyOrder("latitude", "longitude")
class CoordenadaResData : Responsed<Coordenada> {

    var latitude: BigDecimal? = null

    var longitude: BigDecimal? = null

    override fun preencherCom(entidade: Coordenada?) {
        latitude = entidade?.latitude
        longitude = entidade?.longitude
    }
}