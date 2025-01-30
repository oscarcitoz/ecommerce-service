package com.fluxi.order.services

import com.fluxi.order.models.Order
import com.fluxi.order.requests.CreateOrderRequest

interface OrderServiceInterface {
    fun createOrder(request: CreateOrderRequest): Order
} 