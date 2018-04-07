//package rest.data
//
//import com.fasterxml.jackson.annotation.JsonPropertyOrder
//import core.entity.Periodo
//import java.util.*
//import javax.validation.constraints.NotNull
//
//@JsonPropertyOrder("de", "ate")
//class PeriodoData : Data<Periodo, PeriodoData> {
//
//    @NotNull
//    var de: Date? = null
//
//    var ate: Date? = null
//
//    override fun ler(entidade: Periodo?): PeriodoData {
//        this.de = entidade?.de
//        this.ate = entidade?.ate
//
//        return this
//    }
//
//    override fun escrever(entidade: Periodo?): Periodo? {
//        entidade?.de = this.de
//        entidade?.ate = this.ate
//
//        return entidade
//    }
//}