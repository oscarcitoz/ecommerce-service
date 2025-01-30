package com.fluxi.order.directors

import com.fluxi.order.dtos.DirectorDTO
import com.fluxi.order.models.Order
import com.fluxi.order.phases.BasePhase
import com.fluxi.order.requests.CreateOrderRequest
import jakarta.inject.Singleton

@Singleton
class OrderDirector(
    private val phases: List<BasePhase>
) : OrderDirectorInterface {
    override fun make(orderRequest: CreateOrderRequest): Order {
        val dto = DirectorDTO().apply {
            this.request = orderRequest
        }

        this.phases.forEach {
            it.apply(dto)
        }

        return dto.order
    }
}