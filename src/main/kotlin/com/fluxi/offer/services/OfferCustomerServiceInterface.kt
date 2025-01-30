package com.fluxi.offer.services

import com.fluxi.offer.models.OfferCustomer

interface OfferCustomerServiceInterface {
    fun disabledOfferCustomer(orderId: Long): List<OfferCustomer>
    fun findById(id: Long): OfferCustomer?
    fun saveAll(offerCustomers: List<OfferCustomer>): List<OfferCustomer>
    fun findByOrderId(orderId: Long): List<OfferCustomer>
    fun findByOrderIdActive(orderId: Long, productId: String): OfferCustomer?
    fun create(offerCustomer: OfferCustomer, ownerId: String): OfferCustomer?
    fun update(id: Long, offerCustomer: OfferCustomer, ownerId: String): OfferCustomer?
} 