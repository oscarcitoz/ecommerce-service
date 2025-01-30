package com.fluxi.offer.services

import com.fluxi.offer.models.OfferCustomer

interface OfferCustomerServiceInterface {
    fun findById(id: Long): OfferCustomer?
    fun findByOrderId(orderId: Long): List<OfferCustomer>
    fun create(offerCustomer: OfferCustomer, ownerId: String): OfferCustomer?
    fun update(id: Long, offerCustomer: OfferCustomer, ownerId: String): OfferCustomer?
} 