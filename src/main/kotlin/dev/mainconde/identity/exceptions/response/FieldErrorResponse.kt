package dev.mainconde.identity.exceptions.response

data class FieldErrorResponse(
    val message: String,
    val field: String
)
