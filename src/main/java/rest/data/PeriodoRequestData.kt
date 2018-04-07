package rest.data

import core.entity.Periodo
import java.util.*
import javax.validation.constraints.NotNull

class PeriodoRequestData : RequestData<Periodo> {

    @NotNull
    lateinit var de: Date

    var ate: Date? = null

    override fun escrever(entidade: Periodo?): Periodo? {
        entidade?.de = this.de
        entidade?.ate = this.ate

        return entidade
    }
}