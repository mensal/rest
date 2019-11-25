package app.core.entity

import app.core.entity.UsuarioPagamento.UsuarioPagamentoPk
import java.io.Serializable
import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "usuario_pagamento")
@IdClass(UsuarioPagamentoPk::class)
open class UsuarioPagamento() {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    var usuario: Usuario? = null

    @Id
    @ManyToOne
    @JoinColumn(name = "id_pagamento")
    var pagamento: Pagamento<*>? = null

    @NotNull
    @Column(precision = 8, scale = 2)
    open var valor: BigDecimal? = null

    constructor(usuario: Usuario, pagamento: Pagamento<*>?, valor: BigDecimal) : this() {
        this.usuario = usuario
        this.pagamento = pagamento
        this.valor = valor
    }

    internal data class UsuarioPagamentoPk(var usuario: UUID? = null, var pagamento: UUID? = null) : Serializable
}
