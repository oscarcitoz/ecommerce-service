package com.fluxi.offer.repositories

import com.fluxi.offer.models.Offer
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository("offer")
interface OfferRepository : CrudRepository<Offer, Long> {
}
