package com.fluxi.websites.phases.creations

import com.fluxi.core.extensions.BigDecimalExtension.setDefaultScale
import com.fluxi.core.extensions.generateId
import com.fluxi.websites.dtos.WebsiteDirectorDTO
import com.fluxi.websites.models.Website
import com.fluxi.websites.models.WebsiteStatus
import com.fluxi.websites.repositories.WebsiteRepository
import io.micronaut.core.annotation.Order
import jakarta.inject.Singleton
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Order(4)
@Singleton
class WebsitePhase(
    private val websiteRepository: WebsiteRepository
) : BaseCreationPhase {
    override fun apply(dto: WebsiteDirectorDTO): Mono<WebsiteDirectorDTO> {
        dto.website = Website()

        dto.website.id = generateId()
        dto.website.copies = dto.copies
        dto.website.name = dto.request.productName
        dto.website.productDescription = dto.request.productDescription
        dto.website.productWarranties = dto.request.productWarranty
        dto.website.price = dto.request.productPrice
        dto.website.ownerId = dto.request.userIdNotNull()
        dto.website.images = dto.imagesProduct
        dto.website.productId = dto.mainProductId
        dto.website.upsellProductId = dto.upsellProductId
        dto.website.upsellProductPrice = dto.request.upSell?.price
        dto.website.upsellProductImage = dto.upsellImage
        dto.website.downSellProductId = dto.downsellProductId
        dto.website.downSellProductPrice = dto.request.downSell?.price
        dto.website.downSellProductImage = dto.downSellImage
        dto.website.downSellProductPriceWithDiscount = this.calculateDiscount(dto)
        dto.website.status = WebsiteStatus.BUILDING
        dto.website.templateDesign = dto.request.templateDesign
        dto.website.isFreeShipping = dto.request.isFreeShipping

        websiteRepository.save(dto.website)

        return Mono.just(dto)
    }

    private fun calculateDiscount(dto: WebsiteDirectorDTO): BigDecimal? {
        return dto.request.downSell?.let {
            it.price
                .minus(it.price.times(it.percentage.toBigDecimal()).div(BigDecimal(100)))
                .setDefaultScale()
        }
    }
}
