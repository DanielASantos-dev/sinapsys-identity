package dev.mainconde.identity.controller

import dev.mainconde.identity.controller.mapper.PartnerMapper
import dev.mainconde.identity.controller.request.PartnerRequest
import dev.mainconde.identity.controller.response.PartnerResponse
import dev.mainconde.identity.controller.response.ResponsePattern
import dev.mainconde.identity.service.PartnerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/partner")
class PartnerController(private val partnerService: PartnerService, private val partnerMapper: PartnerMapper) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPartner(@Valid @RequestBody partnerRequest: PartnerRequest):ResponsePattern<PartnerResponse>{
        val partnerResponse = partnerMapper.toPartnerResponse(partnerService.createPartner(partnerMapper.toPartnerEntity(partnerRequest))!!)
        return ResponsePattern<PartnerResponse>(
            result = partnerResponse,
            message = "Partner created successfully.",
            success = true,
        )
    }
}
