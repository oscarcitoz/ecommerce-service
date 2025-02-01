package com.fluxi.websites.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.fluxi.order.models.Order
import com.fluxi.order.models.OrderModification
import com.fluxi.order.models.OrderModificationType
import com.fluxi.order.requests.CreateOrderRequest
import com.fluxi.order.requests.ProductRequest
import com.fluxi.order.requests.StoreRequest
import com.fluxi.order.services.OrderServiceInterface
import com.fluxi.websites.directors.WebsiteDirector
import com.fluxi.websites.models.Website
import com.fluxi.websites.models.WebsiteType.Companion.DOWNSELL
import com.fluxi.websites.models.WebsiteType.Companion.UPSELL
import jakarta.inject.Singleton
import com.fluxi.websites.repositories.WebsiteRepository
import com.fluxi.websites.requests.CreateOrderWebsiteRequest
import com.fluxi.websites.requests.CreateWebsiteRequest
import com.fluxi.websites.requests.ModificationRequest
import com.fluxi.websites.requests.UpdateWebsiteRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Singleton
class WebsiteService(
    private val websiteDirector: WebsiteDirector,
    private val websiteRepository: WebsiteRepository,
    private val orderServiceInterface: OrderServiceInterface
) : WebsiteServiceInterface {

    override fun findByOwnerId(ownerId: String): List<Website> =
        websiteRepository.findByOwnerId(ownerId)

    override fun findByProductId(productId: String): List<Website> =
        websiteRepository.findByProductId(productId)

    override fun create(createWebsiteRequest: CreateWebsiteRequest): Mono<Website> {
        return this.websiteDirector.make(createWebsiteRequest)
    }

    override fun findAllWebSites(ownerId: String): List<Website> {
        return this.websiteRepository.findByOwnerId(ownerId)
    }

    override fun findWebSite(ownerId: String, websiteId: String): Website {
        val website = this.websiteRepository.findById(websiteId)
            .orElseThrow { throw HttpStatusException(HttpStatus.BAD_REQUEST, "NOT EXITS WEBSITE") }

        return website
    }

    override fun createOrder(
        websiteId: String,
        createOrderWebsiteRequest: CreateOrderWebsiteRequest,
        ipClient: String
    ): Order {
        val createOrderRequest = CreateOrderRequest().apply {
            this.customer = createOrderWebsiteRequest.customer
            this.customer.ipOrigin = ipClient
        }

        val website = this.websiteRepository.findById(websiteId)
            .orElseThrow { throw HttpStatusException(HttpStatus.BAD_REQUEST, "NOT EXITS WEBSITE") }
        createOrderRequest.product = ProductRequest().apply {
            this.productId = website.productId
            this.units = createOrderWebsiteRequest.units
        }

        createOrderRequest.store = StoreRequest().apply {
            this.ownerId = website.ownerId
            this.email = website.ownerId
        }

        return this.orderServiceInterface.createOrder(createOrderRequest)
    }

    override fun modification(
        websiteId: String,
        hashOrderId: String,
        modificationRequest: ModificationRequest
    ): OrderModification {
        val website = this.websiteRepository.findById(websiteId)
            .orElseThrow { throw HttpStatusException(HttpStatus.BAD_REQUEST, "NOT EXITS WEBSITE") }
        val typeModification = this.getTypeModification(modificationRequest.type, modificationRequest)

        return this.orderServiceInterface.orderModificationHash(OrderModification().apply {
            this.orderModificationType = typeModification
            this.raw = mapOf("product_id" to website.productId, "units" to (modificationRequest.units ?: 0))
        }, hashOrderId)
    }

    override fun updateWebsite(
        ownerId: String,
        websiteId: String,
        updateWebsiteRequest: UpdateWebsiteRequest
    ): Website {
        val website = this.websiteRepository.findById(websiteId)
            .orElseThrow { throw HttpStatusException(HttpStatus.BAD_REQUEST, "NOT EXITS WEBSITE") }

        if (website.ownerId != ownerId) throw HttpStatusException(HttpStatus.BAD_REQUEST, "The website is not your")

        website.url = updateWebsiteRequest.url ?: website.url
        website.status = updateWebsiteRequest.status ?: website.status
        website.updatedAt = LocalDateTime.now()
        this.websiteRepository.updateWebsiteStatusUrlAndUpdatedAt(websiteId, website.status, website.url, website.updatedAt)

        return website
    }

    override fun delete(ownerId: String, websiteId: String): Website {
        val website = this.websiteRepository.findById(websiteId)
            .orElseThrow { throw HttpStatusException(HttpStatus.BAD_REQUEST, "NOT EXITS WEBSITE") }
        if (website.ownerId != ownerId) throw HttpStatusException(HttpStatus.BAD_REQUEST, "The website is not your")

        website.updatedAt = LocalDateTime.now()
        website.deletedAt = LocalDateTime.now()
        this.websiteRepository.updateWebsiteDeletedAtAndUpdatedAt(websiteId, website.updatedAt, website.deletedAt!!)

        return website
    }

    private fun getTypeModification(websiteType: String, modificationRequest: ModificationRequest): String {
        return if (websiteType == UPSELL && modificationRequest.confirm) {
            OrderModificationType.CONFIRM_UP_SELL
        } else if (websiteType == UPSELL && !modificationRequest.confirm) {
            OrderModificationType.DECLINED_UP_SELL
        } else if (websiteType == DOWNSELL && modificationRequest.confirm) {
            OrderModificationType.CONFIRM_DOWN_SELL
        } else {
            OrderModificationType.DECLINED_DOWN_SELL
        }
    }
}
