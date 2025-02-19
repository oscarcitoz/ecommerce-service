package com.fluxi.offer.services

import com.fluxi.offer.models.Offer
import jakarta.inject.Singleton
import com.fluxi.offer.repositories.OfferCustomerRepository
import com.fluxi.offer.models.OfferCustomer
import com.fluxi.offer.repositories.OfferRepository
import io.micronaut.core.annotation.NonNull
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Singleton
class OfferCustomerService(
    private val offerCustomerRepository: OfferCustomerRepository,
    private val offerRepository: OfferRepository
) : OfferCustomerServiceInterface {
    override fun disabledOfferCustomer(orderId: Long): List<OfferCustomer> {
        val offerCustomers = this.findByOrderId(orderId).map {
            it.enabled = false
            it.updatedAt = LocalDateTime.now()
            it
        }.toList()

        return this.offerCustomerRepository.updateAll(offerCustomers)
    }

    override fun findById(id: Long): OfferCustomer? = offerCustomerRepository.findById(id).orElse(null)
    override fun saveAll(offerCustomers: List<OfferCustomer>): List<OfferCustomer> {
        return this.offerCustomerRepository.saveAll(offerCustomers).toList()
    }

    override fun create(offerCustomer: OfferCustomer, ownerId: String): OfferCustomer? {
        return this.offerRepository.findById(offerCustomer.offerId).getOrNull()?.let {
            this.validateOffer(ownerId, it, offerCustomer)
            offerCustomerRepository.save(offerCustomer)
        }
    }

    private fun validateOffer(ownerId: String, offer: Offer, offerCustomer: OfferCustomer) {
        if (offer.ownerId != ownerId || !offer.enabled) {
            throw HttpStatusException(
                HttpStatus.BAD_REQUEST,
                "No tienes permiso para crear una oferta para este producto"
            )
        }

        if (offerCustomer.startsAt.isAfter(offerCustomer.endsAt)) {
            throw HttpStatusException(
                HttpStatus.BAD_REQUEST,
                "La fecha de inicio debe ser anterior a la fecha de fin"
            )
        }

    }

    override fun update(id: Long, offerCustomer: OfferCustomer, ownerId: String): OfferCustomer? {
        return findById(id)?.let { offerCustomerExisting ->
            this.offerRepository.findById(offerCustomer.offerId).getOrNull()?.let {
                this.validateOffer(ownerId, it, offerCustomer)

                offerCustomerExisting.apply {
                    startsAt = offerCustomer.startsAt
                    endsAt = offerCustomer.endsAt
                    updatedAt = LocalDateTime.now()
                }

                offerCustomerRepository.update(offerCustomerExisting)
            }
        }
    }

    override fun findByOrderId(orderId: Long): List<OfferCustomer> =
        offerCustomerRepository.findByOrderId(orderId)

    override fun findByOrderIdActive(orderId: Long, productId: String): OfferCustomer? {
        return this.findByOrderId(orderId).firstOrNull {
            it.productId == productId && it.startsAt.isBefore(LocalDateTime.now()) && it.endsAt.isAfter(
                LocalDateTime.now()
            )
        }
    }
} 