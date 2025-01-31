package com.fluxi.websites.services

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
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

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

    override fun create(createWebsiteRequest: CreateWebsiteRequest): Website {
        return this.websiteDirector.make(createWebsiteRequest)
    }

    override fun findAllWebSites(ownerId: String): List<Website> {
        return this.websiteRepository.findByOwnerId(ownerId)
    }

    override fun findWebSite(ownerId: String, websiteId: String): Website {
        val website = this.websiteRepository.findById(websiteId)
            .orElseThrow { throw HttpStatusException(HttpStatus.BAD_REQUEST, "NOT EXITS WEBSITE") }
        website.upsellId?.let {
            website.upsellWebsite = this.websiteRepository.findById(it).get()
        }

        website.downsellId?.let {
            website.downsellWebsite = this.websiteRepository.findById(it).get()
        }

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
        val typeModification = this.getTypeModification(website.type, modificationRequest)

        return this.orderServiceInterface.orderModificationHash(OrderModification().apply {
            this.orderModificationType = typeModification
            this.raw = mapOf("product_id" to website.productId, "units" to (modificationRequest.units ?: 0))
        }, hashOrderId)
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
