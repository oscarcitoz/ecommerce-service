package com.fluxi.websites.phases.creations

import com.fluxi.core.extensions.generateId
import com.fluxi.websites.dtos.WebsiteDirectorDTO
import com.fluxi.websites.models.Website
import com.fluxi.websites.models.WebsiteStatus
import com.fluxi.websites.models.WebsiteType
import com.fluxi.websites.repositories.WebsiteRepository
import io.micronaut.core.annotation.Order
import jakarta.inject.Singleton

@Order(4)
@Singleton
class WebsitePhase(
    private val websiteRepository: WebsiteRepository
) : BaseCreationPhase {

    override fun apply(dto: WebsiteDirectorDTO): WebsiteDirectorDTO {

        val mainWebsiteProduct = mapWebsiteRequestToDTO(dto)

        println(mainWebsiteProduct)
        return dto
    }

    fun mapWebsiteRequestToDTO(dto: WebsiteDirectorDTO): Website{
        dto.website = Website()

        dto.website.id = generateId()
        dto.website.copies = dto.copys
        dto.website.name = dto.request.productName
        dto.website.ownerId = dto.request.userId.toString()
        dto.website.productId = dto.mainProductId
        dto.website.upsell_id = dto.upsellProductId
        dto.website.downsell_id = dto.downsellProductId
        dto.website.status = WebsiteStatus.BUILDING
        dto.website.type = WebsiteType.LANDING
        dto.website.url = "https://fluxi-bucket.s3.amazonaws.com/"
        dto.website.template_design = dto.request.templateDesign?.filterValues { it != null } as Map<String, Any>?

        val websiteSaved = websiteRepository.save(dto.website)
        return websiteSaved
    }
}
