package dev.mainconde.identity.repository

import dev.mainconde.identity.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {

    fun findByLogin(login: String):UserEntity?
}