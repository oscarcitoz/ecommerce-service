package com.fluxi.order.phases.loaders

import com.fluxi.order.dtos.DirectorDTO
import com.fluxi.store.services.StoreServiceInterface
import io.micronaut.core.annotation.Order
import jakarta.inject.Singleton

@Order(1)
@Singleton
class ProductLoader(private val storeServiceInterface: StoreServiceInterface) : BaseLoadPhase {
    override fun apply(dto: DirectorDTO): DirectorDTO {
        val product = this.storeServiceInterface.findProductById(dto.request.product.productId)
        dto.dependencies.product = product

        return dto
    }
}