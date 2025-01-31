package com.fluxi.store.services

import com.fluxi.core.extensions.BigDecimalExtension.setDefaultScale
import com.fluxi.core.extensions.generateId
import com.fluxi.store.models.Product
import jakarta.inject.Singleton
import com.fluxi.store.repositories.ProductRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import java.time.LocalDateTime
import java.util.UUID
import java.util.NoSuchElementException

@Singleton
class StoreService(private val productRepository: ProductRepository) : StoreServiceInterface {

    override fun findAllProducts(ownerId: String): List<Product> {
        return productRepository.findByOwnerId(ownerId)
            .filter { it.deletedAt == null }
            .toList()
    }

    override fun saveProduct(product: Product): Product {
        product.apply {
            id = generateId()
            price = price.setDefaultScale()
        }

        return productRepository.save(product)
    }

    override fun findProductById(id: String): Product {
        return productRepository.findById(id)
            .filter { it.deletedAt == null }
            .orElseThrow { throw HttpStatusException(HttpStatus.BAD_REQUEST, "Producto no encontrado con ID: $id") }
    }

    override fun findProductByIdAndOwnerId(id: String, ownerId: String): Product {
        return productRepository.findById(id)
            .filter { it.deletedAt == null && it.ownerId == ownerId }
            .orElseThrow { throw HttpStatusException(HttpStatus.BAD_REQUEST, "Producto no encontrado con ID: $id") }
    }

    override fun updateProduct(id: String, product: Product): Product {
        val existingProduct = productRepository.findById(id)
            .filter { it.deletedAt == null }
            .orElseThrow { throw HttpStatusException(HttpStatus.BAD_REQUEST, "Producto no encontrado con ID: $id") }

        if (existingProduct.ownerId != product.ownerId) {
            throw HttpStatusException(HttpStatus.BAD_REQUEST, "No tienes permiso para actualizar este producto")
        }

        existingProduct.apply {
            name = product.name
            price = product.price.setDefaultScale()
            available = product.available
            images = product.images
            description = product.description
            category = product.category
            updatedAt = LocalDateTime.now()
        }

        return productRepository.update(existingProduct)
    }
} 