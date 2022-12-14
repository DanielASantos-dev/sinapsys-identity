package dev.mainconde.identity.service

import dev.mainconde.identity.entity.UserEntity
import dev.mainconde.identity.entity.enum.Role
import dev.mainconde.identity.exceptions.InternalServerError
import dev.mainconde.identity.exceptions.NotFoundException
import dev.mainconde.identity.exceptions.enums.Errors
import dev.mainconde.identity.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserRepository, private val bCrypt: BCryptPasswordEncoder) {

    //@Transactional
    fun createUser(userEntity: UserEntity): UserEntity{
   try {
            val userCopy = userEntity.copy(
                password = bCrypt.encode(userEntity.password)
            )
             return userRepository.save(userCopy)
        } catch (ex: Exception) {
            ex.printStackTrace()
           throw InternalServerError(Errors.SI201.message, Errors.SI201.internalCode)
        }
    }

    fun findById(id: Long): UserEntity{
        return userRepository.findById(id)
            .orElseThrow{NotFoundException(Errors.SI200.message.format(id), Errors.SI200.internalCode)}
    }
}