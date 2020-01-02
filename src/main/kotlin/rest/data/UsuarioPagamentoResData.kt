package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.UsuarioPagamento
import java.math.BigDecimal

@JsonPropertyOrder("valor", "usuario")
class UsuarioPagamentoResData : Responsed<UsuarioPagamento> {

    var valor: BigDecimal? = null

    var usuario: UsuarioResData? = null

    override fun preencherCom(entidade: UsuarioPagamento?) {
        valor = entidade?.valor

        if (usuario == null) usuario = UsuarioResData()
        usuario!!.preencherCom(entidade?.usuario)
    }
}