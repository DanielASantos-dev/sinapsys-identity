package dev.mainconde.identity.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UserRequest(

    @field: NotBlank
    val login: String,

    @field:NotBlank
    val password: String,

    @JsonAlias("password_confirmation")
    @field:NotBlank
    val passwordConfirmation: String,

    @JsonAlias("partner_id")
    @field:NotNull
    val partnerId: Long,

)
