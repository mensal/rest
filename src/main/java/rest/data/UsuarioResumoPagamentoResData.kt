package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.UsuarioResumoPagamento
import java.math.BigDecimal

@JsonPropertyOrder("usuario", "atual", "atual")
open class UsuarioResumoPagamentoResData : ResData<UsuarioResumoPagamento> {

    var usuario: UsuarioResData? = null

    var atual: BigDecimal? = null

    var anterior: BigDecimal? = null

    override fun preencherCom(entidade: UsuarioResumoPagamento?) {
        if (usuario == null) usuario = UsuarioResData()
        usuario!!.preencherCom(entidade?.usuario)

        atual = entidade?.atual
        anterior = entidade?.anterior
    }
}