package com.fluxi.order.services

import com.fluxi.order.directors.OrderDirector
import com.fluxi.order.models.Order
import com.fluxi.order.models.OrderModification
import com.fluxi.order.modifications.BaseOrderModification
import com.fluxi.order.repositories.OrderModificationRepository
import jakarta.inject.Singleton
import com.fluxi.order.repositories.OrderRepository
import com.fluxi.order.requests.CreateOrderRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

@Singleton
class OrderService(
    private val orderDirector: OrderDirector,
    private val orderModifications: List<BaseOrderModification>,
    private val orderModificationRepository: OrderModificationRepository,
) : OrderServiceInterface {
    override fun createOrder(request: CreateOrderRequest): Order {
        return this.orderDirector.make(request)
    }

    override fun orderModification(orderModification: OrderModification): OrderModification {
        val orderModificationClass =
            this.orderModifications.firstOrNull { it.getModificationType() == orderModification.orderModificationType }

        if (orderModificationClass == null) throw HttpStatusException(
            HttpStatus.BAD_REQUEST,
            "Order modification type not exists"
        )

        return this.orderModificationRepository.save(orderModificationClass.makeModification(orderModification))
    }
} 