package app.rest.security

import app.core.entity.Usuario
import app.core.persistence.UsuarioDAO
import app.rest.UnauthorizedException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class Autenticador protected constructor() {

    @Autowired
    lateinit var usuarioDAO: UsuarioDAO

    var logado: Usuario? = null

    fun autenticar(token: String) {
        val claims = Jwts.parser().setSigningKey(chave()).parseClaimsJws(token)

        val id = UUID.fromString(claims.body.subject)
        logado = usuarioDAO.obter(id) ?: throw UnauthorizedException()
    }

    fun autenticar(login: String, senha: String): String {
        val usuario = usuarioDAO.obter(login) ?: throw UnauthorizedException()

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, chave())
                .setSubject(usuario.id.toString())
                .claim("name", usuario.nome)
                .setIssuer("http://teste")
                .setExpiration(Date.from(Instant.now().plus(5, ChronoUnit.DAYS)))
                .setIssuedAt(Date.from(Instant.now()))
                .compact()
    }

    private fun chave() = Base64.getEncoder().encodeToString(System.getenv("MENSAL_JWT_KEY").toByteArray())
}
