package rest.security

import core.entity.Usuario
import core.persistence.UsuarioDAO
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import rest.UnauthorizedException
import java.time.Instant
import java.util.*
import javax.enterprise.context.RequestScoped
import javax.enterprise.inject.spi.CDI

@RequestScoped
open class Autenticador protected constructor() {

    open var logado: Usuario? = null

    open fun autenticar(token: String) {
        val claims = Jwts.parser().setSigningKey(chave()).parseClaimsJws(token)

        val id = UUID.fromString(claims.body.subject)
        logado = UsuarioDAO.instance().obter(id) ?: throw UnauthorizedException()
    }

    open fun autenticar(login: String, senha: String): String {
        val usuario = UsuarioDAO.instance().obter(login) ?: throw UnauthorizedException()

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, chave())
                .setSubject(usuario.id.toString())
                .claim("name", usuario.nome)
                .setIssuer("http://teste")
                .setIssuedAt(Date.from(Instant.now()))
                .compact()
    }

    private fun chave() = Base64.getEncoder().encodeToString(System.getenv("AUTENTICACAO_CHAVE_JWT").toByteArray())

    companion object {

        fun instance() = CDI.current().select(Autenticador::class.java).get()!!
    }
}