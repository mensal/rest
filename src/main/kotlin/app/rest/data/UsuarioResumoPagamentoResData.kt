package app.rest.data

import app.core.entity.UsuarioResumoPagamento
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.math.BigDecimal

@JsonPropertyOrder("usuario", "atual", "anterior")
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