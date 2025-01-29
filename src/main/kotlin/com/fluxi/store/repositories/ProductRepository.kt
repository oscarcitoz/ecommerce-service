package com.fluxi.store.repositories

import com.fluxi.store.models.Product
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface ProductRepository : CrudRepository<Product, String> {
    fun findByOwnerId(ownerId: String): List<Product>
    fun update(product: Product): Product
}