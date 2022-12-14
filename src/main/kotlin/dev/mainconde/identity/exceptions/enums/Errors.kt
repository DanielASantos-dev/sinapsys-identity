package dev.mainconde.identity.exceptions.enums

enum class Errors(val message: String, val internalCode: String) {

    SI001("Invalid Request", "SI-001"),

    SI100("Parceiro de id %s não foi encontrado", "SI-100"),
    SI101("Ocorreu um erro ao salvar o parceiro", "SI-101"),

    SI200("Usuário de id %s não foi encontrado", "SI-200"),
    SI201("Ocorreu um erro ao salvar o usuário", "SI-201"),

    SI1000("Passwords do not match", "SI1001")


}