package dev.mainconde.identity.repository

import dev.mainconde.identity.entity.PartnerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PartnerRepository: JpaRepository<PartnerEntity, Long>