package com.fluxi.store.controllers

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import com.fluxi.store.services.StoreServiceInterface

@Controller("/store")
class StoreController(private val storeService: StoreServiceInterface) {
    
    @Get("product/")
    fun findAll() = storeService.findAll()
} 