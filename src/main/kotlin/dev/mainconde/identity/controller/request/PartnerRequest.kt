package dev.mainconde.identity.controller.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class PartnerRequest(

    @field:NotBlank
    val taxNumber: String,

    @field:NotBlank
    val name: String,

    @field: Email
    val email: String,
    )

