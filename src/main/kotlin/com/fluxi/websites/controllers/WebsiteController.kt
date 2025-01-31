package com.fluxi.websites.controllers

import com.fluxi.websites.requests.CreateWebsiteRequest
import io.micronaut.http.annotation.*
import com.fluxi.websites.services.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fluxi.order.models.Order
import com.fluxi.order.models.OrderModification
import com.fluxi.websites.models.Website
import com.fluxi.websites.requests.CreateOrderWebsiteRequest
import com.fluxi.websites.requests.ModificationRequest
import reactor.core.publisher.Mono

@Controller("/websites")
class WebsiteController(
    private val websiteService: WebsiteServiceInterface
) {
    @Post("/")
    fun create(@Body websiteRequest: CreateWebsiteRequest): Mono<Website> {
        return websiteService.create(websiteRequest)
    }

    @Get("/")
    fun findAll(
        @Header("owner-id") ownerId: String
    ): List<Website> {
        return websiteService.findAllWebSites(ownerId)
    }

    @Get("/{websiteId}")
    fun find(
        @PathVariable(value = "websiteId") websiteId: String,
        @Header("owner-id") ownerId: String
    ): Website {
        return websiteService.findWebSite(ownerId, websiteId)
    }

    @Post("/{websiteId}/create-order")
    fun createOrder(
        @Header("X-Forwarded-For") ipClient: String,
        @PathVariable(value = "websiteId") websiteId: String,
        @Body createOrderWebsiteRequest: CreateOrderWebsiteRequest
    ): Order {
        return websiteService.createOrder(websiteId, createOrderWebsiteRequest, ipClient)
    }

    @Put("/{websiteId}/modification/{hashOrderId}")
    fun modification(
        @PathVariable(value = "websiteId") websiteId: String,
        @PathVariable(value = "hashOrderId") hashOrderId: String,
        @Body modificationRequest: ModificationRequest
    ): OrderModification {
        return websiteService.modification(websiteId, hashOrderId, modificationRequest)
    }
}
