package dev.mainconde.identity.controller.response

import dev.mainconde.identity.entity.enum.Role

data class UserResponse(
    val user: String,
    val role: Set<Role>
    )