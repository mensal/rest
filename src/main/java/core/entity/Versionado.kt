package core.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.Version
import javax.validation.constraints.NotNull

@MappedSuperclass
abstract class Versionado {

    @Version
    @NotNull
    @Column(name = "atualizado_em", columnDefinition = "timestamp with time zone")
    var atualizadoEm: Date? = null
}