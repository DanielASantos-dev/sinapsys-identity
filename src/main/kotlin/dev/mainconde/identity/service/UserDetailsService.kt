package dev.mainconde.identity.service

import dev.mainconde.identity.exceptions.AuthenticationException
import dev.mainconde.identity.repository.UserRepository
import dev.mainconde.identity.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService(
    private val userRepository: UserRepository
    ): UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        val user = userRepository.findByLogin(login)

        if (user == null){
            AuthenticationException("Usuario não encontrado", "9999")
        }

        return UserCustomDetails(user!!)
    }
}