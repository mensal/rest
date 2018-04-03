package core.entity

import java.util.*
import javax.persistence.*

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
}