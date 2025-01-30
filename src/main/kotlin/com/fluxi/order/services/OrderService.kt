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
import io.micronaut.transaction.annotation.Transactional

@Singleton
open class OrderService(
    private val orderDirector: OrderDirector,
    private val orderModifications: List<BaseOrderModification>,
    private val orderModificationRepository: OrderModificationRepository,
    private val orderRepository: OrderRepository
) : OrderServiceInterface {
    override fun createOrder(request: CreateOrderRequest): Order {
        return this.orderDirector.make(request)
    }

    @Transactional("order")
    override fun orderModification(orderModification: OrderModification): OrderModification {
        val orderModificationClass =
            this.orderModifications.firstOrNull { it.getModificationType() == orderModification.orderModificationType }

        if (orderModificationClass == null) throw HttpStatusException(
            HttpStatus.BAD_REQUEST,
            "Order modification type not exists"
        )

        val order = this.orderRepository.findById(orderModification.orderId)
            .orElseThrow { throw HttpStatusException(HttpStatus.BAD_REQUEST, "Order Not Exists") }
        if (!order.isActiveOrder()) throw HttpStatusException(HttpStatus.BAD_REQUEST, "Order Not Active")

        return this.orderModificationRepository.save(orderModificationClass.makeModification(orderModification))
    }

    @Transactional("order")
    override fun orderModificationHash(orderModification: OrderModification, hashId: String): OrderModification {
        val order = this.orderRepository.findByHashId(hashId)
            .orElseThrow { throw HttpStatusException(HttpStatus.BAD_REQUEST, "Order Not Exists") }

        orderModification.orderId = order.id!!

        return this.orderModification(orderModification)
    }
} 