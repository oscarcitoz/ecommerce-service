package com.fluxi.websites.repositories

import com.fluxi.websites.models.Website
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import reactor.core.publisher.Flux

@Repository("website")
interface WebsiteRepository : CrudRepository<Website, String> {
    fun findByProductId(productId: String): List<Website>
    fun findByOwnerId(ownerId: String): List<Website>
}
