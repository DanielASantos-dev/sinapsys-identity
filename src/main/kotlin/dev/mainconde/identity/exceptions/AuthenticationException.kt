package dev.mainconde.identity.exceptions

class AuthenticationException(override val message: String, val code: String) : Exception()