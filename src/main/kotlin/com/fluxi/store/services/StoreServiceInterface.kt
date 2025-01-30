package com.fluxi.store.services

import com.fluxi.store.models.Product

interface StoreServiceInterface {
    fun findAllProducts(ownerId: String): List<Product>
    fun saveProduct(product: Product): Product
    fun findProductById(id: String): Product
    fun findProductByIdAndOwnerId(id: String, ownerId: String): Product
    fun updateProduct(id: String, product: Product): Product
} 