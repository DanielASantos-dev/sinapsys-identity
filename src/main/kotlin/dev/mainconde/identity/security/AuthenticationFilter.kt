package dev.mainconde.identity.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import dev.mainconde.identity.controller.request.LoginRequest
import dev.mainconde.identity.controller.response.LoginResponse
import dev.mainconde.identity.exceptions.AuthenticationException
import dev.mainconde.identity.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.PrintWriter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil
): UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val loginRequest = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)
            val login = userRepository.findByLogin(loginRequest.login)?.login
            val authToken = UsernamePasswordAuthenticationToken(login, loginRequest.password)
            return authenticationManager.authenticate(authToken)
        }catch (ex: Exception){
            throw AuthenticationException("Falha ao autenticar", "00000")
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {

        val login = (authResult.principal as UserCustomDetails).login
        val userEntity = userRepository.findByLogin(login)
        val token = jwtUtil.generateToken(userEntity!!)
        response.addHeader("Authorization", "Bearer $token")

        val loginResponse = LoginResponse(
            accessToken = token,
            expiresIn = jwtUtil.getExpirationMilliseconds().toString() + " ms",
            dueDateTime = jwtUtil.convertTime().toString(),
            tokenType = "Bearer"
        )

        val loginResponseString = Gson().toJson(loginResponse)

        val out: PrintWriter = response.writer

        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"

        out.print(loginResponseString)
        out.flush()
    }
}