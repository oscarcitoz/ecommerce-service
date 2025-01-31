package com.fluxi.offer.services

import com.fluxi.offer.models.Offer

interface OfferServiceInterface {
    fun findById(id: Long): Offer?
    fun create(offer: Offer): Offer
    fun update(id: Long, offer: Offer): Offer?
    fun findByOwnerId(ownerId: String): List<Offer>
    fun findByProductId(productId: String): List<Offer>
} 