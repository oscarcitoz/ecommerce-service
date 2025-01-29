package com.fluxi.store.services

import jakarta.inject.Singleton
import com.fluxi.store.repositories.ProductRepository

@Singleton
class StoreService(private val storeRepository: ProductRepository) : StoreServiceInterface {
    
    override fun findAll() = storeRepository.findAll()
} 