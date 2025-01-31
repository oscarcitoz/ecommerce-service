package com.fluxi.order.phases.creations

import com.fluxi.core.extensions.BigDecimalExtension.setDefaultScale
import com.fluxi.order.dtos.DirectorDTO
import com.fluxi.order.models.OrderProduct
import com.fluxi.order.repositories.OrderProductRepository
import io.micronaut.core.annotation.Order
import jakarta.inject.Singleton

@Order(4)
@Singleton
class OrderProductPhase(private val orderProductRepository: OrderProductRepository) : BaseCreationPhase {
    override fun apply(dto: DirectorDTO): DirectorDTO {
        val product = dto.dependencies.product

        dto.orderProduct = this.orderProductRepository.save(
            OrderProduct().apply {
                this.orderId = dto.order.id!!
                this.productId = product.id
                this.name = product.name
                this.images = product.images
                this.unitPrice = product.price.setDefaultScale()
                this.units = dto.request.product.units
            }
        )

        return dto
    }
}