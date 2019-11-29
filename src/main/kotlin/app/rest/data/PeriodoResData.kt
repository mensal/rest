package app.rest.data

import app.core.entity.Periodo
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.time.LocalDate

@JsonPropertyOrder("de", "ate")
class PeriodoResData : ResData<Periodo> {

    var de: LocalDate? = null

    var ate: LocalDate? = null

    override fun preencherCom(entidade: Periodo?) {
        de = entidade?.de
        ate = entidade?.ate
    }
}