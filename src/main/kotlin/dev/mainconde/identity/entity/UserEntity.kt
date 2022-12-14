package dev.mainconde.identity.entity

import dev.mainconde.identity.entity.enum.Role
import dev.mainconde.identity.entity.enum.UserStatusEnum
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(


    @Column(name = "id_user")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idUser: Long? = null,

    @Column(nullable = false, unique = true)
    val login: String,

    @Column(nullable = false)
    val password: String,

    @ManyToOne
    @JoinColumn(name = "partner_id")
    val partner: PartnerEntity,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
    var roles: Set<Role> = setOf(),

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val status: UserStatusEnum = UserStatusEnum.ACTIVE,
)
