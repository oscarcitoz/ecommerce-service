package com.fluxi.order.services

import jakarta.inject.Singleton
import com.fluxi.order.repositories.OrderRepository
import com.fluxi.order.requests.CreateOrderRequest

@Singleton
class OrderService(private val orderRepository: OrderRepository) : OrderServiceInterface {
    override fun createOrder(request: CreateOrderRequest) {

    }

} 