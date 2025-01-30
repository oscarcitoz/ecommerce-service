package com.fluxi.order.phases

import com.fluxi.order.dtos.DirectorDTO
import com.fluxi.order.models.OrderStore
import com.fluxi.order.repositories.OrderStoreRepository
import io.micronaut.core.annotation.Order
import jakarta.inject.Singleton

@Order(3)
@Singleton
class OrderStorePhase(
    private val orderStoreRepository: OrderStoreRepository
) : BasePhase {
    override fun apply(dto: DirectorDTO): DirectorDTO {
        val store = dto.request.store

        dto.orderStore = orderStoreRepository.save(
            OrderStore().apply {
                this.orderId = dto.order.id!!
                this.ownerId = store.ownerId
                this.email = store.email
            }
        )

        return dto
    }
}