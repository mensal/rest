package rest.security

import core.entity.Usuario
import core.persistence.UsuarioDAO
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.eclipse.microprofile.config.inject.ConfigProperty
import rest.UnauthorizedException
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import javax.enterprise.context.RequestScoped
import javax.inject.Inject

@RequestScoped
open class Autenticador protected constructor() {

    @Inject
    private lateinit var usuarioDAO: UsuarioDAO

    @ConfigProperty(name = "mensal.jwt.key")
    lateinit var jwtKey: String

    open var logado: Usuario? = null

    open fun autenticar(token: String) {
        val claims = Jwts.parser().setSigningKey(chave()).parseClaimsJws(token)

        val id = UUID.fromString(claims.body.subject)
        logado = usuarioDAO.obter(id) ?: throw UnauthorizedException()
    }

    open fun autenticar(login: String, senha: String): String {
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

    private fun chave() = Base64.getEncoder().encodeToString(jwtKey.toByteArray())
}
