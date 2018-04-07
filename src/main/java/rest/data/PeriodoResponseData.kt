package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.Periodo
import java.util.*

@JsonPropertyOrder("de", "ate")
class PeriodoResponseData : ResponseData<Periodo, PeriodoResponseData> {

    var de: Date? = null

    var ate: Date? = null

    override fun ler(entidade: Periodo?): PeriodoResponseData {
        this.de = entidade?.de
        this.ate = entidade?.ate

        return this
    }
}