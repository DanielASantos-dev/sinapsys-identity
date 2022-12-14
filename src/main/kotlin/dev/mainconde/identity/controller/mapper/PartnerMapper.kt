package dev.mainconde.identity.controller.mapper

import dev.mainconde.identity.controller.request.PartnerRequest
import dev.mainconde.identity.controller.response.PartnerResponse
import dev.mainconde.identity.entity.PartnerEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class PartnerMapper {
    fun toPartnerEntity(partnerRequest: PartnerRequest):PartnerEntity{
        return PartnerEntity(
            taxNumber = partnerRequest.taxNumber,
            name = partnerRequest.name,
            email = partnerRequest.email,
            clientKey = UUID.randomUUID().toString()
        )
    }

    fun toPartnerResponse(partnerEntity: PartnerEntity): PartnerResponse{
        return PartnerResponse(
            id = partnerEntity.idPartner!!,
            taxNumber = partnerEntity.taxNumber,
            name = partnerEntity.name,
            email = partnerEntity.email,
            clientKey = partnerEntity.clientKey.toString()
        )
    }
}