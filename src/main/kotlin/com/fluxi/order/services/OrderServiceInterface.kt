package com.fluxi.order.services

import com.fluxi.order.models.Order
import com.fluxi.order.models.OrderModification
import com.fluxi.order.requests.CreateOrderRequest

interface OrderServiceInterface {
    fun createOrder(request: CreateOrderRequest): Order
    fun orderModification(orderModification: OrderModification): OrderModification
    fun orderModificationHash(orderModification: OrderModification, hashId: String): OrderModification
} 