package dev.mainconde.identity.entity

import javax.persistence.*

@Entity
@Table(name = "partners")
data class PartnerEntity(

    @Column(name = "id_partner")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idPartner: Long? = null,

    @Column(name = "tax_number", nullable = false, unique = true, length =  15)
    val taxNumber: String,

    @Column(nullable = false, length =  150)
    val name: String,

    @Column(nullable = false, unique = true, length =  100)
    val email: String,

    @Column(name = "client_key", nullable = false, unique = true)
    val clientKey: String?,
)
