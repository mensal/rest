package app.rest.data

import app.core.entity.UsuarioPagamento
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.math.BigDecimal

@JsonPropertyOrder("valor", "usuario")
class UsuarioPagamentoResData : ResData<UsuarioPagamento> {

    var valor: BigDecimal? = null

    var usuario: UsuarioResData? = null

    override fun preencherCom(entidade: UsuarioPagamento?) {
        valor = entidade?.valor

        if (usuario == null) usuario = UsuarioResData()
        usuario!!.preencherCom(entidade?.usuario)
    }
}