package com.fluxi.websites.services

import com.fluxi.order.models.Order
import com.fluxi.order.models.OrderModification
import com.fluxi.websites.models.Website
import com.fluxi.websites.requests.CreateOrderWebsiteRequest
import com.fluxi.websites.requests.CreateWebsiteRequest
import com.fluxi.websites.requests.ModificationRequest

interface WebsiteServiceInterface {
    fun findByOwnerId(ownerId: String): List<Website>
    fun findByProductId(productId: String): List<Website>
    fun create(createWebsiteRequest: CreateWebsiteRequest): Website
    fun findAllWebSites(ownerId: String): List<Website>
    fun findWebSite(ownerId: String, websiteId: String): Website
    fun createOrder(websiteId: String, createOrderWebsiteRequest: CreateOrderWebsiteRequest, ipClient: String): Order
    fun modification(websiteId: String, hashOrderId: String, modificationRequest: ModificationRequest): OrderModification
}

