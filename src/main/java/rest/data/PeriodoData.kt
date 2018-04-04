package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.Periodo
import java.util.*

@JsonPropertyOrder("nome", "desde")
class PeriodoData {

    var de: Date? = null

    var ate: Date? = null

    fun preencher(periodo: Periodo?): PeriodoData {
        this.de = periodo?.de
        this.ate = periodo?.ate

        return this
    }

    fun converter(): Periodo {
        val convertido = Periodo()
        convertido.de = this.de
        convertido.ate = this.ate

        return convertido
    }
}