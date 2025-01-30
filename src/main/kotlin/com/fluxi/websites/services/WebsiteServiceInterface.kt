package com.fluxi.websites.services

import com.fluxi.websites.models.Website

interface WebsiteServiceInterface {
    fun findByOwnerId(ownerId: String): List<Website>
    fun findByProductId(productId: String): List<Website>
    fun create(website: Website): Website
    fun update(id: Long, website: Website): Website
    fun delete(id: Long): Unit
}

