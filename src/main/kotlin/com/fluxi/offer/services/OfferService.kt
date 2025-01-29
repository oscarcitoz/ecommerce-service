package com.fluxi.offer.services

import jakarta.inject.Singleton
import com.fluxi.offer.repositories.OfferRepository
import com.fluxi.offer.models.Offer
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import java.time.LocalDateTime

@Singleton
class OfferService(private val offerRepository: OfferRepository) : OfferServiceInterface {

    override fun findById(id: Long): Offer? = offerRepository.findById(id).orElse(null)

    override fun create(offer: Offer): Offer {
        if (this.offerRepository.findByProductId(offer.productId).isNotEmpty()) {
            throw HttpStatusException(HttpStatus.BAD_REQUEST, "Ya existe una oferta para este producto")
        }

        offer.createdAt = LocalDateTime.now()
        offer.updatedAt = LocalDateTime.now()


        return offerRepository.save(offer)
    }

    override fun update(id: Long, offer: Offer): Offer? {
        val existingOffer = findById(id) ?: return null

        if (existingOffer.ownerId != offer.ownerId) {
            throw HttpStatusException(HttpStatus.BAD_REQUEST, "No tienes permiso para actualizar esta oferta")
        }

        existingOffer.apply {
            name = offer.name
            description = offer.description
            enabled = offer.enabled
            discountType = offer.discountType
            discountValue = offer.discountValue
            productId = offer.productId
            updatedAt = LocalDateTime.now()
        }

        return offerRepository.update(existingOffer)
    }

    override fun findByOwnerId(ownerId: String) = offerRepository.findByOwnerId(ownerId)

    override fun findByProductId(productId: String) = offerRepository.findByProductId(productId)
} 