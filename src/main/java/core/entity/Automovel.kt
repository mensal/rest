package core.entity

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "automovel")
open class Automovel {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @Version
    var versao: Int? = null

    @NotNull
    @Embedded
    var periodo: Periodo? = null

    @NotNull
    var nome: String? = null
}