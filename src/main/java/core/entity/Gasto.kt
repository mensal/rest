package core.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
internal class Gasto {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @ManyToOne
    @JoinColumn(name = "id_lancamento")
    var lancamento: Lancamento? = null

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    var usuario: Usuario? = null

    @NotNull
    @Column(precision = 8, scale = 2)
    var valor: BigDecimal? = null
}