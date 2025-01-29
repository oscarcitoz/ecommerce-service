package com.fluxi.store.services

import com.fluxi.store.models.Product

interface StoreServiceInterface {
    fun findAll(ownerId: String): List<Product>
    fun save(product: Product): Product
    fun findById(id: String): Product?
    fun update(id: String, product: Product): Product
} 