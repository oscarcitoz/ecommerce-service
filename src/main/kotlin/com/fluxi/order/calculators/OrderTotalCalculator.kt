package com.fluxi.order.calculators

import com.fluxi.core.extensions.BigDecimalExtension.setDefaultScale
import com.fluxi.order.dtos.DirectorDTO
import com.fluxi.order.repositories.OrderProductRepository
import com.fluxi.order.repositories.OrderRepository
import jakarta.inject.Singleton

@Singleton
class OrderTotalCalculator(
    private val orderRepository: OrderRepository,
    private val orderProductRepository: OrderProductRepository
) : OrderTotalCalculatorInterface {
    override fun calculateCreation(directorDTO: DirectorDTO): DirectorDTO {
        val orderProduct = directorDTO.orderProduct
        val order = directorDTO.order

        orderProduct.apply {
            this.totalValue = orderProduct.unitPrice.multiply(orderProduct.units.toBigDecimal()).setDefaultScale()
            this.unitPriceWithDiscount = orderProduct.unitPrice
            this.totalValueWithDiscount = orderProduct.unitPrice.multiply(orderProduct.units.toBigDecimal()).setDefaultScale()
        }

        order.totalValue = orderProduct.totalValue
        order.totalValueWithDiscount = orderProduct.totalValueWithDiscount

        this.orderRepository.save(order)
        this.orderProductRepository.save(orderProduct)

        return directorDTO
    }
}