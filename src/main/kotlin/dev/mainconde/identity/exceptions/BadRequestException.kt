package dev.mainconde.identity.exceptions

class BadRequestException(override val message: String, val code: String) : Exception()