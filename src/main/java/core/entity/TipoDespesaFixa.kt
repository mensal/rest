package core.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tipo_despesa_fixa")
internal class TipoDespesaFixa {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID? = null

    @Version
    val versao: Long? = null
}