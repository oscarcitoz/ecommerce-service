package com.fluxi.websites.phases.creations

import com.fluxi.offer.models.DiscountType
import com.fluxi.offer.models.Offer
import com.fluxi.offer.services.OfferService
import com.fluxi.store.models.Product
import com.fluxi.store.services.StoreService
import com.fluxi.websites.dtos.WebsiteDirectorDTO
import io.micronaut.core.annotation.Order
import jakarta.inject.Singleton
import java.math.BigDecimal

@Order(2)
@Singleton
class SaveProductsPhase(
    private val storeService: StoreService,
    private val offerService: OfferService
): BaseCreationPhase {
    override fun apply(dto: WebsiteDirectorDTO): WebsiteDirectorDTO {

        val productSaved = createMainProduct(dto)

        val upsellProductSaved = createUpsellProduct(dto)

        val downsellOffer = createUpsellOffer(upsellProductSaved.id, dto)

        println(productSaved)
        println(upsellProductSaved)
        println(downsellOffer)

        dto.mainProductId = productSaved.id
        dto.upsellProductId = upsellProductSaved.id
        dto.downsellProductId = upsellProductSaved.id

        return dto
    }

    private fun createMainProduct(dto: WebsiteDirectorDTO): Product {
        val mainProduct = Product().apply {
            ownerId = dto.request.userId ?: ""
            name = dto.request.productName
            price = BigDecimal(dto.request.productPrice?.toDouble() ?: 0.0)
            available = true
            images = listOf("http:...1", "http:...2")
            description = dto.request.productDescription
            category = null
        }

        println("Producto creado: $mainProduct")

        return storeService.saveProduct(mainProduct)
    }

    private fun createUpsellProduct(dto: WebsiteDirectorDTO): Product {
        val upsellProduct = Product().apply {
            ownerId = dto.request.userId ?: ""
            name = dto.request.upSell.name
            price = BigDecimal(dto.request.upSell.price?.toDouble() ?: 0.0)
            available = true
            images = listOf("http:...1", "http:...2")
            description = dto.request.upSell.name
            category = null
        }

        println("Producto Upsell creado: $upsellProduct")

        return storeService.saveProduct(upsellProduct)
    }

    private fun createUpsellOffer(productIdUpsell: String, dto: WebsiteDirectorDTO): Offer {
        val upsellProduct = Offer().apply {
            ownerId = dto.request.userId ?: ""
            name = dto.request.upSell.name
            description = dto.request.upSell.name
            enabled = true
            discountType = DiscountType.PERCENTAGE
            discountValue = (dto.request.downSell.price?.toDouble() ?: 0.0).toBigDecimal()
            productId = productIdUpsell
        }

        println("Producto Upsell creado: $upsellProduct")

        return offerService.create(upsellProduct)
    }


}