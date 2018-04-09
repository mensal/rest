package rest.data

import core.entity.UsuarioPagamento
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.NotNull

class UsuarioPagamentoReqData : ReqData<UsuarioPagamento> {

    @NotNull
    lateinit var valor: BigDecimal

    @Valid
    @NotNull
    lateinit var usuario: IdReqData

    override fun escreverEm(entidade: UsuarioPagamento) {
        entidade.valor = valor
    }
}