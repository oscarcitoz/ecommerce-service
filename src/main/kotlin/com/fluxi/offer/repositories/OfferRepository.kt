package com.fluxi.offer.repositories

import com.fluxi.offer.models.Offer
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository("offer")
interface OfferRepository : CrudRepository<Offer, Long> {
    fun findByOwnerId(ownerId: String): List<Offer>
    fun findByProductId(productId: String): List<Offer>
    fun findByEnabled(enabled: Boolean): List<Offer>
}
