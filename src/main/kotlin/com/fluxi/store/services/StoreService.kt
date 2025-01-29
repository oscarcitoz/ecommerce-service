package com.fluxi.store.services

import com.fluxi.core.extensions.generateId
import com.fluxi.store.models.Product
import jakarta.inject.Singleton
import com.fluxi.store.repositories.ProductRepository
import java.time.LocalDateTime
import java.util.UUID
import java.util.NoSuchElementException

@Singleton
class StoreService(private val productRepository: ProductRepository) : StoreServiceInterface {

    override fun findAll(ownerId: String): List<Product> {
        return productRepository.findByOwnerId(ownerId)
            .filter { it.deletedAt == null }
            .toList()
    }

    override fun save(product: Product): Product {
        product.apply {
            id = generateId()
        }

        return productRepository.save(product)
    }

    override fun findById(id: String): Product? {
        return productRepository.findById(id)
            .filter { it.deletedAt == null }
            .orElse(null)
    }

    override fun update(id: String, product: Product): Product {
        val existingProduct = productRepository.findById(id)
            .filter { it.deletedAt == null }
            .orElseThrow { NoSuchElementException("Producto no encontrado con ID: $id") }

        if (existingProduct.ownerId != product.ownerId) {
            throw IllegalArgumentException("No tienes permiso para actualizar este producto")
        }

        existingProduct.apply {
            name = product.name
            price = product.price
            available = product.available
            images = product.images
            description = product.description
            category = product.category
            updatedAt = LocalDateTime.now()
        }

        return productRepository.update(existingProduct)
    }
} 