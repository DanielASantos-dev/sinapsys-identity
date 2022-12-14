package dev.mainconde.identity.controller.mapper

import dev.mainconde.identity.controller.request.UserRequest
import dev.mainconde.identity.controller.response.UserResponse
import dev.mainconde.identity.entity.UserEntity
import dev.mainconde.identity.entity.enum.Role
import dev.mainconde.identity.service.PartnerService
import org.springframework.stereotype.Component

@Component
class UserMapper(private val partnerService: PartnerService) {

    fun createUserEntity(userRequest: UserRequest): UserEntity{
        val partner = partnerService.findById(userRequest.partnerId)

        if (userRequest.login == partner.email){
            return UserEntity(
                login = userRequest.login,
                password = userRequest.password,
                partner = partner,
                roles = setOf(Role.ADMIN)
            )
        }

        return UserEntity(
            login = userRequest.login,
            password = userRequest.password,
            partner = partner,
            roles = setOf(Role.COLLABORATOR)
        )
    }

    fun toUserResponse(userEntity: UserEntity):UserResponse{
        return UserResponse(
            user = userEntity.login,
            role = userEntity.roles
        )
    }
}