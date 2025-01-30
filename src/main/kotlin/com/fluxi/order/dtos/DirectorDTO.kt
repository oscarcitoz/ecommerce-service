package com.fluxi.order.dtos

import com.fluxi.order.models.*
import com.fluxi.order.requests.CreateOrderRequest

class DirectorDTO {
    lateinit var request: CreateOrderRequest
    lateinit var order: Order
    lateinit var orderProduct: OrderProduct
    lateinit var orderProductOffer: OrderProductOffer
    lateinit var orderCustomer: OrderCustomer
    lateinit var orderStore: OrderStore
}