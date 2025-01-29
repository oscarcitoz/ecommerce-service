package com.fluxi.offer.controllers

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import com.fluxi.offer.services.OfferServiceInterface

@Controller("/offer")
class OfferController(private val offerService: OfferServiceInterface) {
    
    @Get("/")
    fun findAll() = offerService.findAll()
} 