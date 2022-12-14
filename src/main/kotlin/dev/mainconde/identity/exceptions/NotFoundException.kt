package dev.mainconde.identity.exceptions

class NotFoundException(override val message: String, val code: String ): Exception()