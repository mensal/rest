package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.Periodo
import java.util.*

@JsonPropertyOrder("de", "ate")
class PeriodoData : Data<Periodo> {

    var de: Date? = null

    var ate: Date? = null

    override fun ler(periodo: Periodo?): PeriodoData {
        this.de = periodo?.de
        this.ate = periodo?.ate

        return this
    }

    override fun escrever(periodo: Periodo?): Periodo? {
        periodo?.de = this.de
        periodo?.ate = this.ate

        return periodo
    }
}