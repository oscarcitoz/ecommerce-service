package com.fluxi.websites.phases.creations

import com.fluxi.offer.models.DiscountType
import com.fluxi.offer.models.Offer
import com.fluxi.offer.services.OfferService
import com.fluxi.store.models.Product
import com.fluxi.store.services.StoreService
import com.fluxi.websites.dtos.WebsiteDirectorDTO
import io.micronaut.core.annotation.Order
import jakarta.inject.Singleton
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Order(3)
@Singleton
class SaveProductsPhase(
    private val storeService: StoreService,
    private val offerService: OfferService
) : BaseCreationPhase {
    override fun apply(dto: WebsiteDirectorDTO): Mono<WebsiteDirectorDTO> {
        val productSaved = this.createProduct(
            dto,
            dto.request.productName,
            dto.request.productDescription,
            dto.imagesProduct,
            dto.request.productPrice
        )

        dto.request.upSell?.let {
            val upsellProductSaved = this.createProduct(
                dto,
                it.name,
                it.name,
                listOf(dto.upsellImage ?: ""),
                it.price
            )

            if (dto.request.downSell != null) {
                createUpsellOffer(upsellProductSaved.id, dto)
            }

            dto.upsellProductId = upsellProductSaved.id
            dto.downsellProductId = upsellProductSaved.id
        }

        dto.mainProductId = productSaved.id


        return Mono.just(dto)
    }

    private fun createProduct(
        dto: WebsiteDirectorDTO,
        productName: String,
        description: String,
        images: List<String>,
        price: BigDecimal,
    ): Product {
        val product = Product().apply {
            this.ownerId = dto.request.userIdNotNull()
            this.name = productName
            this.price = price
            this.available = true
            this.images = images
            this.description = description
            this.category = null
        }

        return storeService.saveProduct(product)
    }

    private fun createUpsellOffer(productIdUpsell: String, dto: WebsiteDirectorDTO): Offer {
        val upsellProduct = Offer().apply {
            ownerId = dto.request.userIdNotNull()
            name = dto.request.upSell!!.name
            description = dto.request.upSell!!.name
            enabled = true
            discountType = DiscountType.PERCENTAGE
            discountValue = (dto.request.downSell!!.percentage.toBigDecimal())
            productId = productIdUpsell
        }

        return offerService.create(upsellProduct)
    }
}