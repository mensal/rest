package rest.data

import core.entity.PagamentoDiversa
import core.entity.TipoDespesaDiversa
import core.entity.Usuario
import core.entity.UsuarioPagamento
import org.hibernate.validator.constraints.NotEmpty
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotNull

class PagamentoDiversaReqData : ReqData<PagamentoDiversa> {

    @NotNull
    lateinit var data: LocalDate

    var observacao: String? = null

    @Valid
    @NotNull
    lateinit var tipo: IdReqData

    @Valid
    @NotEmpty
    lateinit var valores: List<UsuarioPagamentoReqData>

    override fun escreverEm(entidade: PagamentoDiversa) {
        if (entidade.tipo == null) entidade.tipo = TipoDespesaDiversa()
        tipo.escreverEm(entidade.tipo!!)

        entidade.data = data
        entidade.observacao = observacao
        entidade.valores = valores.map { UsuarioPagamento(Usuario(it.usuario.id), null, it.valor) }
    }
}
