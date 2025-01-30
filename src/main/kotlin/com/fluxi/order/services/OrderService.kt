package com.fluxi.order.services

import com.fluxi.order.directors.OrderDirector
import com.fluxi.order.models.Order
import jakarta.inject.Singleton
import com.fluxi.order.repositories.OrderRepository
import com.fluxi.order.requests.CreateOrderRequest

@Singleton
class OrderService(private val orderDirector: OrderDirector) : OrderServiceInterface {
    override fun createOrder(request: CreateOrderRequest): Order {
        return this.orderDirector.make(request)
    }
} 