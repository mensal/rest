package core.entity

import java.util.*
import javax.persistence.*
import javax.persistence.InheritanceType.JOINED
import javax.persistence.TemporalType.TIMESTAMP
import javax.validation.constraints.NotNull

@Entity
@Table(name = "lancamento")
@Inheritance(strategy = JOINED)
open class Lancamento {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @NotNull
    @Temporal(TemporalType.DATE)
    var data: Date? = null

    @NotNull
    @Temporal(TIMESTAMP)
    @Column(name = "atualizado_em", columnDefinition = "timestamp with time zone")
    var atualizadoEm: Date? = null

    @PreUpdate
    @PrePersist
    private fun pre() {
        atualizadoEm = Date()
    }
}