package core.entity

import java.math.BigDecimal

class UsuarioResumoPagamento {

    var usuario: Usuario

    var valor: BigDecimal

    var anterior: BigDecimal

    constructor(usuario: Usuario, valor: BigDecimal, anterior: BigDecimal) {
        this.usuario = usuario
        this.valor = valor
        this.anterior = anterior
    }
}
