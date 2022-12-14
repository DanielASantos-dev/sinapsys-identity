package dev.mainconde.identity.controller.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class LoginRequest(

    @field:Email
    val login: String,

    @field:NotBlank
    val password: String
)
