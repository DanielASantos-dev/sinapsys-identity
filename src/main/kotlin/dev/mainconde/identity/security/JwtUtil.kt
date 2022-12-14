package dev.mainconde.identity.security

import com.fasterxml.jackson.annotation.JsonAlias
import dev.mainconde.identity.entity.UserEntity
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

@Component
class JwtUtil(
    @Value("\${jwt.expiration}")
    private val expirationMilliseconds: Long? = null,

    @Value("\${jwt.secret}")
    private val secret : String? = null,

    var expirationSeconds: Long? = null
) {

    init {
        expirationSeconds = expirationMilliseconds!! / 1000
    }


    fun generateToken(userEntity: UserEntity): String {

        return Jwts.builder()
            .claim("login", userEntity.login)
            .claim("partner", userEntity.partner)
            .claim("roles", userEntity.roles)
            .claim("status", userEntity.status)
            .setExpiration(Date(System.currentTimeMillis() + expirationSeconds!!))
            .signWith(SignatureAlgorithm.HS512, secret!!.toByteArray())
            .compact()
    }

    fun getExpirationMilliseconds(): Long{
        return expirationMilliseconds!!
    }

    fun convertTime(): String {
        print(expirationSeconds)
        val currentTime = LocalDateTime.now().plusSeconds(expirationSeconds!!)
        return SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
    }
}
