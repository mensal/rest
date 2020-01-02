package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.UsuarioResumoPagamento
import java.math.BigDecimal

@JsonPropertyOrder("usuario", "atual", "anterior")
open class UsuarioResumoPagamentoResData : Responsed<UsuarioResumoPagamento> {

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