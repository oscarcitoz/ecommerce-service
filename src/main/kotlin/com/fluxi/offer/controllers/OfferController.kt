package com.fluxi.offer.controllers

import io.micronaut.http.annotation.*
import io.micronaut.http.HttpStatus
import com.fluxi.offer.services.OfferServiceInterface
import com.fluxi.offer.models.Offer

@Controller("/offer")
class OfferController(private val offerService: OfferServiceInterface) {
    @Get("/{id}")
    fun findById(@PathVariable(value = "id") id: Long): Offer? {
        return offerService.findById(id)
    }

    @Post("/")
    @Status(HttpStatus.CREATED)
    fun create(@Body offer: Offer, @Header("owner-id") ownerId: String): Offer {
        offer.ownerId = ownerId

        return offerService.create(offer)
    }

    @Put("/{id}")
    fun update(@PathVariable(value = "id") id: Long, @Body offer: Offer, @Header("owner-id") ownerId: String): Offer? {
        offer.ownerId = ownerId

        return offerService.update(id, offer)
    }

    @Get("/owner/{ownerId}")
    fun findByOwnerId(@PathVariable(value = "ownerId") ownerId: String): List<Offer> {
        return offerService.findByOwnerId(ownerId)
    }

    @Get("/product/{productId}")
    fun findByProductId(@PathVariable(value = "productId") productId: String): List<Offer> {
        return offerService.findByProductId(productId)
    }
} 