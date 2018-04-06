package core.entity

import core.entity.UsuarioPagamento.UsuarioPagamentoPk
import java.io.Serializable
import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "usuario_pagamento")
@IdClass(UsuarioPagamentoPk::class)
open class UsuarioPagamento {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    var usuario: Usuario? = null

    @Id
    @ManyToOne
    @JoinColumn(name = "id_pagamento")
    var pagamento: Pagamento? = null

    @NotNull
    @Column(precision = 8, scale = 2)
    var valor: BigDecimal? = null

    internal class UsuarioPagamentoPk : Serializable {

        var usuario: UUID? = null

        var pagamento: UUID? = null

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as UsuarioPagamentoPk

            if (usuario != other.usuario) return false
            if (pagamento != other.pagamento) return false

            return true
        }

        override fun hashCode(): Int {
            var result = usuario?.hashCode() ?: 0
            result = 31 * result + (pagamento?.hashCode() ?: 0)
            return result
        }
    }
}
