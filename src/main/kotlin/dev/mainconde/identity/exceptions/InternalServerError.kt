package dev.mainconde.identity.exceptions

class InternalServerError(override val message: String, val code: String): Exception()