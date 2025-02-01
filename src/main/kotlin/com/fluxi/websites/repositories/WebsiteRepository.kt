package com.fluxi.websites.repositories

import com.fluxi.websites.models.Website
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import reactor.core.publisher.Flux
import java.time.LocalDateTime

@Repository("website")
interface WebsiteRepository : CrudRepository<Website, String> {
    fun findByProductId(productId: String): List<Website>
    fun findByOwnerId(ownerId: String): List<Website>
    @Query("UPDATE Website SET status = :status, url = :url, updatedAt = :updatedAt WHERE id = :id")
    fun updateWebsiteStatusUrlAndUpdatedAt(
        id: String,
        status: String,
        url: String,
        updatedAt: LocalDateTime
    ): Int

    @Query("UPDATE Website SET updatedAt = :updatedAt, deletedAt = :deletedAt WHERE id = :id")
    fun updateWebsiteDeletedAtAndUpdatedAt(
        id: String,
        updatedAt: LocalDateTime,
        deletedAt: LocalDateTime
    ): Int
}
