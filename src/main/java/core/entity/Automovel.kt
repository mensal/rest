package core.entity

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
internal class Automovel {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @Version
    val versao: Long? = null

    @NotNull
    @Temporal(TemporalType.DATE)
    var desde: Date? = null

    @Column
    @Temporal(TemporalType.DATE)
    var ate: Date? = null

    @NotNull
    var nome: String? = null
}