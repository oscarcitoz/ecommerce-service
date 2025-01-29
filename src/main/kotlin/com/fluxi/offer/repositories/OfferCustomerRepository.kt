package com.fluxi.offer.repositories

import com.fluxi.offer.models.OfferCustomer
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.time.LocalDateTime

@Repository("offer")
interface OfferCustomerRepository : CrudRepository<OfferCustomer, Long> {
} 