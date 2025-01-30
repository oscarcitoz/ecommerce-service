package com.fluxi.offer.repositories

import com.fluxi.offer.models.OfferCustomer
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository("offer")
interface OfferCustomerRepository : CrudRepository<OfferCustomer, Long> {
    fun findByOfferId(offerId: Long): List<OfferCustomer>
    fun findByOrderId(orderId: Long): List<OfferCustomer>
} 