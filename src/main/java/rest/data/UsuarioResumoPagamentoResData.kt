package rest.data

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import core.entity.UsuarioResumoPagamento
import java.math.BigDecimal

@JsonPropertyOrder("usuario", "valor", "anterior")
open class UsuarioResumoPagamentoResData : ResData<UsuarioResumoPagamento> {

    var usuario: UsuarioResData? = null

    var valor: BigDecimal? = null

    var anterior: BigDecimal? = null

    override fun preencherCom(entidade: UsuarioResumoPagamento?) {
        if (usuario == null) usuario = UsuarioResData()
        usuario!!.preencherCom(entidade?.usuario)

        valor = entidade?.valor
        anterior = entidade?.anterior
    }
}