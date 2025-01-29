package com.fluxi.store.controllers

import io.micronaut.http.annotation.*
import com.fluxi.store.services.StoreServiceInterface
import com.fluxi.store.models.Product
import io.micronaut.http.HttpStatus

@Controller("/store")
class StoreController(
    private val storeService: StoreServiceInterface
) {

    @Get("product/")
    fun findAll(@Header("owner-id") ownerId: String): List<Product> {
        return storeService.findAll(ownerId)
    }

    @Get("/product/{id}")
    fun findById(
        @PathVariable(value = "id") id: String,
        @Header("owner-id") ownerId: String
    ): Product? {
        return storeService.findById(id)
    }

    @Post("/product")
    @Status(HttpStatus.CREATED)
    fun save(
        @Header("owner-id") ownerId: String,
        @Body product: Product
    ): Product {
        product.ownerId = ownerId

        return storeService.save(product)
    }

    @Put("/product/{id}")
    fun update(
        @Header("owner-id") ownerId: String,
        @Body product: Product,
        @PathVariable(value = "id") id: String,
    ): Product {
        product.ownerId = ownerId

        return storeService.update(id, product)
    }
} 