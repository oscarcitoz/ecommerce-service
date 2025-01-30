package com.fluxi.order.calculators

import com.fluxi.offer.models.OfferCustomer
import com.fluxi.order.dtos.DirectorDTO
import com.fluxi.order.models.Order
import com.fluxi.order.models.OrderProduct

interface OrderTotalCalculatorInterface {
    fun calculateCreation(directorDTO: DirectorDTO): DirectorDTO
    fun calculateTotalProduct(orderProduct: OrderProduct, offerCustomer: OfferCustomer?): OrderProduct
    fun calculateTotalOrder(orderProducts: List<OrderProduct>, order: Order): Order
}