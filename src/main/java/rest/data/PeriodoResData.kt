package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.Periodo
import java.util.*

@JsonPropertyOrder("de", "ate")
class PeriodoResData : ResData<Periodo, PeriodoResData> {

    var de: Date? = null

    var ate: Date? = null

    override fun ler(entidade: Periodo?): PeriodoResData {
        this.de = entidade?.de
        this.ate = entidade?.ate

        return this
    }
}