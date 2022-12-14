package dev.mainconde.identity.exceptions

data class PasswordInvalidException(override val message: String, val errorCode: String): Exception()