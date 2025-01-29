package com.fluxi.order.controllers

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import com.fluxi.order.services.OrderServiceInterface

@Controller("/order")
class OrderController(private val orderService: OrderServiceInterface) {
    
    @Get("/")
    fun findAll() = orderService.findAll()
} 