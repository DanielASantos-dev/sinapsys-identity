package dev.mainconde.identity.security

import dev.mainconde.identity.entity.UserEntity
import dev.mainconde.identity.entity.enum.UserStatusEnum
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCustomDetails(private val userEntity: UserEntity): UserDetails{
    val login = userEntity.login
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = userEntity
        .roles.map{ SimpleGrantedAuthority(it.description) }.toMutableList()

    override fun getPassword(): String = userEntity.password

    override fun getUsername(): String = userEntity.login

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = userEntity.status == UserStatusEnum.ACTIVE
}