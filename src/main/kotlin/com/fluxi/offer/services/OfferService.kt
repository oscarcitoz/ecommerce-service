package com.fluxi.offer.services

import jakarta.inject.Singleton
import com.fluxi.offer.repositories.OfferRepository

@Singleton
class OfferService(private val offerRepository: OfferRepository) : OfferServiceInterface {
    
    override fun findAll() = offerRepository.findAll()
} 