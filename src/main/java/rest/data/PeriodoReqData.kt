package rest.data

import core.entity.Periodo
import java.time.LocalDate
import javax.validation.constraints.NotNull

class PeriodoReqData : ReqData<Periodo> {

    @NotNull
    lateinit var de: LocalDate

    var ate: LocalDate? = null

    override fun escreverEm(entidade: Periodo?) {
        entidade?.de = this.de
        entidade?.ate = this.ate
    }
}