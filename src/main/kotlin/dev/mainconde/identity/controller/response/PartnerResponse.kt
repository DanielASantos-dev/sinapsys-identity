package dev.mainconde.identity.controller.response

data class PartnerResponse(
    val id: Long,
    val taxNumber: String,
    val name: String,
    val email: String,
    val clientKey: String
)
