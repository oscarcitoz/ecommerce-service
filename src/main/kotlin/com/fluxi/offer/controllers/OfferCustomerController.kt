package com.fluxi.offer.controllers

import io.micronaut.http.annotation.*
import io.micronaut.http.HttpStatus
import com.fluxi.offer.services.OfferCustomerServiceInterface
import com.fluxi.offer.models.OfferCustomer

@Controller("/offer-customer")
class OfferCustomerController(private val offerCustomerService: OfferCustomerServiceInterface) {
    @Post("/")
    @Status(HttpStatus.CREATED)
    fun create(@Body offerCustomer: OfferCustomer, @Header("owner-id") ownerId: String): OfferCustomer? {
        return offerCustomerService.create(offerCustomer, ownerId)
    }

    @Put("/{id}")
    fun update(id: Long, @Body offerCustomer: OfferCustomer, @Header("owner-id") ownerId: String): OfferCustomer? {
        return offerCustomerService.update(id, offerCustomer, ownerId)
    }

    @Get("/order/{orderId}")
    fun findByOrderId(orderId: Long): List<OfferCustomer> {
        return offerCustomerService.findByOrderId(orderId)
    }
} 