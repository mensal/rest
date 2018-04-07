package core.entity

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@MappedSuperclass
abstract class Versionado {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @Version
    @NotNull
    @Column(name = "atualizado_em", columnDefinition = "timestamp with time zone")
    var atualizadoEm: Date? = null
}