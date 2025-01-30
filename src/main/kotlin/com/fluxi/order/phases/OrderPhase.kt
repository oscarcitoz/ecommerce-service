package com.fluxi.order.phases

import com.fluxi.order.dtos.DirectorDTO
import com.fluxi.order.models.Order
import com.fluxi.order.models.OrderState
import com.fluxi.order.repositories.OrderRepository
import jakarta.inject.Singleton

@io.micronaut.core.annotation.Order(1)
@Singleton
class OrderPhase(private val orderRepository: OrderRepository): BasePhase {
    override fun apply(dto: DirectorDTO): DirectorDTO {
        dto.order = this.orderRepository.save(Order().apply {
            this.state = OrderState.CREATED
        })

        return dto
    }
}