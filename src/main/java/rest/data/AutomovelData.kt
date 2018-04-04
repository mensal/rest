package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.Automovel
import java.util.*

@JsonPropertyOrder("id", "nome", "periodo", "versao")
class AutomovelData {

    var id: UUID? = null

    var nome: String? = null

    var periodo: PeriodoData? = null

    var versao: Int? = null

    fun ler(automovel: Automovel?): AutomovelData {
        this.id = automovel?.id
        this.nome = automovel?.nome
        this.periodo = PeriodoData().ler(automovel?.periodo)

        return this
    }

    fun escrever(automovel: Automovel?): Automovel? {
        automovel?.id = this.id
        automovel?.nome = this.nome
        automovel?.periodo = periodo?.escrever(automovel?.periodo)

        return automovel
    }
}