package core.entity

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
open class Coordenada {

    @Column(name = "gps_longitude", precision = 20, scale = 2)
    var longitude: BigDecimal? = null

    @Column(name = "gps_latitude", precision = 20, scale = 2)
    var latitude: BigDecimal? = null
}