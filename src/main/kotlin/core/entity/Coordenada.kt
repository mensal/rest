package core.entity

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Coordenada {

    @Column(name = "gps_longitude", precision = 20, scale = 17)
    var longitude: BigDecimal? = null

    @Column(name = "gps_latitude", precision = 20, scale = 17)
    var latitude: BigDecimal? = null
}