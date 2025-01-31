package com.fluxi.websites.phases.creations

import com.fluxi.core.extensions.generateId
import com.fluxi.websites.dtos.WebsiteDirectorDTO
import com.fluxi.websites.models.Website
import com.fluxi.websites.models.WebsiteStatus
import com.fluxi.websites.models.WebsiteType
import com.fluxi.websites.repositories.WebsiteRepository
import io.micronaut.core.annotation.Order
import jakarta.inject.Singleton
import java.math.BigDecimal

@Order(4)
@Singleton
class WebsitePhase(
    private val websiteRepository: WebsiteRepository
) : BaseCreationPhase {
    override fun apply(dto: WebsiteDirectorDTO): WebsiteDirectorDTO {
        val upsellWebsite = this.createWebsite(
            dto.upsellProductId,
            dto.request.upSell.name,
            dto.request.userIdNotNull(),
            WebsiteStatus.BUILDING,
            WebsiteType.UPSELL,
            "https://fluxi-bucket.s3.amazonaws.com/",
            dto.request.templateDesign,
            dto.request.upSell.price
        )

        val downWebsite = if (dto.request.downSell != null) {
            this.createWebsite(
                dto.downsellProductId,
                dto.request.upSell.name,
                dto.request.userIdNotNull(),
                WebsiteStatus.BUILDING,
                WebsiteType.DOWNSELL,
                "https://fluxi-bucket.s3.amazonaws.com/",
                dto.request.templateDesign,
                dto.request.downSell?.price ?: BigDecimal.ZERO
            )
        } else null


        val mainWebsiteProduct = mapWebsiteRequestToDTO(dto, upsellWebsite.id, downWebsite?.id)

        return dto
    }

    private fun createWebsite(
        productId: String,
        name: String,
        userId: String,
        status: String,
        type: String,
        url: String,
        templateDesign: Map<String, Any>?,
        price: BigDecimal
    ): Website {
        val website = Website()

        website.id = generateId()
        website.name = name
        website.ownerId = userId
        website.productId = productId
        website.status = status
        website.type = type
        website.url = url
        website.templateDesign = templateDesign
        website.price = price

        return websiteRepository.save(website)
    }

    private fun mapWebsiteRequestToDTO(dto: WebsiteDirectorDTO, upsellId: String, downsellId: String?): Website {
        dto.website = Website()

        dto.website.id = generateId()
        dto.website.copies = dto.copys
        dto.website.name = dto.request.productName
        dto.website.price = dto.request.productPrice
        dto.website.ownerId = dto.request.userIdNotNull()
        dto.website.productId = dto.mainProductId
        dto.website.upsellId = upsellId
        dto.website.downsellId = downsellId
        dto.website.status = WebsiteStatus.BUILDING
        dto.website.type = WebsiteType.LANDING
        dto.website.url = "https://fluxi-bucket.s3.amazonaws.com/"
        dto.website.templateDesign = dto.request.templateDesign

        val websiteSaved = websiteRepository.save(dto.website)
        return websiteSaved
    }
}
