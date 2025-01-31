package com.fluxi.order.dtos

import com.fluxi.order.models.*
import com.fluxi.order.requests.CreateOrderRequest
import com.fluxi.store.models.Product

class DirectorDTO {
    lateinit var request: CreateOrderRequest
    lateinit var order: Order
    lateinit var orderProduct: OrderProduct
    lateinit var orderCustomer: OrderCustomer
    lateinit var orderStore: OrderStore

    var dependencies: LoadDependency = LoadDependency()
}

class LoadDependency {
    lateinit var product: Product
}