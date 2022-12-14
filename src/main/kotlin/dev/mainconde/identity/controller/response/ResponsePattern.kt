package dev.mainconde.identity.controller.response

data class ResponsePattern<T>(
    val result: T?,
    val success: Boolean,
    val message: String,
)