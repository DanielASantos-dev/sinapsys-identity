package dev.mainconde.identity.exceptions.response

data class ErrorResponse(
    val message: String,
    val internalCodeError: String,
    val errors: List<FieldErrorResponse>?
)