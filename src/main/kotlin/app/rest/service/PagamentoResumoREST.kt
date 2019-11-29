package app.rest.service

import app.core.persistence.UsuarioResumoPagamentoDAO
import app.core.util.autowired
import app.rest.UnprocessableEntityException
import app.rest.data.UsuarioResumoPagamentoResData
import app.rest.service.CrudREST.Companion.lancarExcecaoSeNecessario
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/pagamento/resumo")
class PagamentoResumoREST {

    private val violationException = UnprocessableEntityException()

    //    @Logado
    @GetMapping
    fun resumo(@RequestParam("ano") ano: Int?, @RequestParam("mes") mes: Int?): List<UsuarioResumoPagamentoResData>? {
        lancarExcecaoSeNecessario(violationException)

        val persistidos = autowired(UsuarioResumoPagamentoDAO::class).pesquisar(ano!!, mes!!)

        val resultado = persistidos.map {
            val entidade = it

            val data = UsuarioResumoPagamentoResData()
            data.preencherCom(entidade)
            data
        }

        return if (resultado.isEmpty()) null else resultado
    }
}
