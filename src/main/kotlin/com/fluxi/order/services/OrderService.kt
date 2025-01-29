package com.fluxi.order.services

import jakarta.inject.Singleton
import com.fluxi.order.repositories.OrderRepository

@Singleton
class OrderService(private val orderRepository: OrderRepository) : OrderServiceInterface {
    
    override fun findAll() = orderRepository.findAll()
} 