package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.Periodo
import java.time.LocalDate

@JsonPropertyOrder("de", "ate")
class PeriodoResData : ResData<Periodo> {

    var de: LocalDate? = null

    var ate: LocalDate? = null

    override fun preencherCom(entidade: Periodo?) {
        this.de = entidade?.de
        this.ate = entidade?.ate
    }
}