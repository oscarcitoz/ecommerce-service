package com.fluxi.order.controllers

import com.fluxi.order.models.Order
import com.fluxi.order.models.OrderModification
import com.fluxi.order.requests.CreateOrderRequest
import com.fluxi.order.services.OrderServiceInterface
import io.micronaut.http.annotation.*

@Controller("/order")
class OrderController(private val orderService: OrderServiceInterface) {

    @Post
    fun createOrder(
        @Header("X-Forwarded-For") ipClient: String,
        @Body request: CreateOrderRequest
    ): Order {
        request.customer.ipOrigin = ipClient

        return orderService.createOrder(request)
    }

    @Put("{orderId}/{modificationType}")
    fun createOrderModification(
        @Body request: Map<String, Any>?,
        @PathVariable(value = "orderId") orderId: Long,
        @PathVariable(value = "modificationType") modificationType: String,
    ): OrderModification {
        val orderModification = OrderModification().apply {
            this.orderId = orderId
            this.orderModificationType = modificationType
            this.raw = request
        }

        return orderService.orderModification(orderModification)
    }
} 