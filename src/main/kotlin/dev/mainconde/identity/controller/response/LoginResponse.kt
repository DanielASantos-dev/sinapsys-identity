package dev.mainconde.identity.controller.response

import com.fasterxml.jackson.annotation.JsonAlias
import dev.mainconde.identity.entity.PartnerEntity
import dev.mainconde.identity.entity.enum.Role
import dev.mainconde.identity.entity.enum.UserStatusEnum

data class LoginResponse(
    @JsonAlias("access_token")
    val accessToken: String,

    @JsonAlias("expires_in")
    val expiresIn: String,

    @JsonAlias("due_date_time")
    val dueDateTime: String,

    @JsonAlias("token_type")
    val tokenType: String
)
