package core.entity

import java.util.*
import javax.persistence.*
import javax.persistence.InheritanceType.JOINED
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tipo_despesa")
@Inheritance(strategy = JOINED)
abstract class TipoDespesa : Versionado() {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @NotNull
    @Embedded
    var periodo: Periodo? = null
}