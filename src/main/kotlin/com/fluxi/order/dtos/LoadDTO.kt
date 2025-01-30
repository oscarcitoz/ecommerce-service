package com.fluxi.order.dtos

import com.fluxi.order.models.*
import com.fluxi.order.requests.CreateOrderRequest

class LoadDTO {
    lateinit var request: CreateOrderRequest
    
    //DEPENDENCIES
    //lateinit var clientResponse: UserResponse

    lateinit var order: Order
    lateinit var orderProduct: OrderProduct
    lateinit var orderOffer: OrderOffer
    lateinit var orderCustomer: OrderCustomer
    lateinit var orderStore: OrderStore
}