package com.fluxi.order.modifications

import com.fluxi.order.models.OrderModification
import com.fluxi.order.models.OrderModificationType
import com.fluxi.order.repositories.OrderRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton
import java.time.LocalDateTime

@Singleton
class ChangeStateOrderModification(
    private val orderRepository: OrderRepository
) : BaseOrderModification {
    override fun makeModification(orderModification: OrderModification): OrderModification {
        val state = orderModification.raw?.get("state") as String
        val order = this.orderRepository.findById(orderModification.orderId).orElseThrow {
            throw HttpStatusException(
                HttpStatus.BAD_REQUEST, "Order Not Exists"
            )
        }

        order.state = state
        order.updatedAt = LocalDateTime.now()
        this.orderRepository.update(order)

        return orderModification
    }

    override fun getModificationType(): String {
        return OrderModificationType.CHANGE_STATE
    }
}