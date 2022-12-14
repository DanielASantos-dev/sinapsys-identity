package dev.mainconde.identity.service

import dev.mainconde.identity.entity.PartnerEntity
import dev.mainconde.identity.exceptions.InternalServerError
import dev.mainconde.identity.exceptions.NotFoundException
import dev.mainconde.identity.exceptions.enums.Errors
import dev.mainconde.identity.repository.PartnerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class PartnerService(private val partnerRepository: PartnerRepository) {

    @Transactional
    fun createPartner(partnerEntity: PartnerEntity):PartnerEntity?{
       return try {
           partnerRepository.save(partnerEntity)
       }catch (ex: Exception){
           InternalServerError(Errors.SI101.message, Errors.SI101.internalCode)
           return null
       }
    }

    fun findById(partnerId: Long): PartnerEntity {
        return partnerRepository.findById(partnerId)
            .orElseThrow {NotFoundException(Errors.SI100.message.format(partnerId), Errors.SI100.internalCode)}
    }

}