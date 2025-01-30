package com.fluxi.order.controllers

import com.fluxi.order.models.Order
import com.fluxi.order.requests.CreateOrderRequest
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import com.fluxi.order.services.OrderServiceInterface
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Post

@Controller("/order")
class OrderController(private val orderService: OrderServiceInterface) {

    @Post
    fun createOrder(@Header("X-Forwarded-For") ipClient: String,
                    @Body request: CreateOrderRequest): Order {
        request.customer.ipOrigin = ipClient

        return orderService.createOrder(request)
    }
} 