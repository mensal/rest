package rest.data

import core.entity.Usuario
import core.entity.UsuarioPagamento
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.NotNull

class PagamentoValorReqData : ReqData<UsuarioPagamento> {

    @Valid
    @NotNull
    lateinit var usuario: IdReqData

    @NotNull
    lateinit var valor: BigDecimal

    override fun escreverEm(entidade: UsuarioPagamento) {
        if (entidade.usuario == null) entidade.usuario = Usuario()
        entidade.usuario!!.id = usuario.id

        entidade.valor = valor
    }
}