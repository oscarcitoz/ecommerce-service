package com.fluxi.websites.phases.creations

import com.fluxi.core.extensions.BigDecimalExtension.setDefaultScale
import com.fluxi.core.extensions.generateId
import com.fluxi.websites.dtos.WebsiteDirectorDTO
import com.fluxi.websites.models.Website
import com.fluxi.websites.models.WebsiteStatus
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
        dto.website = Website()

        //TODO IMAGES
        dto.website.id = generateId()
        dto.website.copies = dto.copys
        dto.website.name = dto.request.productName
        dto.website.price = dto.request.productPrice
        dto.website.ownerId = dto.request.userIdNotNull()
        dto.website.images = listOf()
        dto.website.productId = dto.mainProductId
        dto.website.upsellProductId = dto.upsellProductId
        dto.website.upsellProductPrice = dto.request.upSell.price
        dto.website.upsellProductImage = ""
        dto.website.downSellProductId = dto.downsellProductId
        dto.website.downSellProductPrice = dto.request.downSell?.price
        dto.website.downSellProductImage = ""
        dto.website.downSellProductPriceWithDiscount = this.calculateDiscount(dto)
        dto.website.status = WebsiteStatus.BUILDING
        dto.website.templateDesign = dto.request.templateDesign

        websiteRepository.save(dto.website)

        return dto
    }

    private fun calculateDiscount(dto: WebsiteDirectorDTO): BigDecimal? {
        return dto.request.downSell?.let {
            it.price
                .minus(it.price.times(it.percentage.toBigDecimal()).div(BigDecimal(100)))
                .setDefaultScale()
        }
    }
}
