package com.fluxi.order.directors

import com.fluxi.order.models.Order
import com.fluxi.order.requests.CreateOrderRequest

interface OrderDirectorInterface {
    fun make(orderRequest: CreateOrderRequest): Order
}