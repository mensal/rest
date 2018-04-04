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

    fun preencher(automovel: Automovel?): AutomovelData {
        this.id = automovel?.id
        this.nome = automovel?.nome
        this.periodo = PeriodoData().preencher(automovel?.periodo)
        this.versao = automovel?.versao

        return this
    }

    fun converter(): Automovel {
        val convertido = Automovel()

        convertido.id = this.id
        convertido.nome = this.nome
        convertido.periodo = this.periodo?.converter()
        convertido.versao = this.versao

        return convertido
    }
}