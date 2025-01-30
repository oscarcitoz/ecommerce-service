package com.fluxi.order.calculators

import com.fluxi.core.extensions.BigDecimalExtension.setDefaultScale
import com.fluxi.offer.models.DiscountType
import com.fluxi.offer.models.OfferCustomer
import com.fluxi.order.dtos.DirectorDTO
import com.fluxi.order.models.Order
import com.fluxi.order.models.OrderProduct
import com.fluxi.order.repositories.OrderProductRepository
import com.fluxi.order.repositories.OrderRepository
import jakarta.inject.Singleton
import java.math.BigDecimal
import java.time.LocalDateTime

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
            this.totalValueWithDiscount =
                orderProduct.unitPrice.multiply(orderProduct.units.toBigDecimal()).setDefaultScale()
        }

        order.totalValue = orderProduct.totalValue
        order.totalValueWithDiscount = orderProduct.totalValueWithDiscount

        this.orderRepository.save(order)
        this.orderProductRepository.save(orderProduct)

        return directorDTO
    }

    override fun calculateTotalProduct(orderProduct: OrderProduct, offerCustomer: OfferCustomer?): OrderProduct {
        val unitPriceWithDiscount = offerCustomer?.let {
            if (it.discountType == DiscountType.PERCENTAGE) {
                orderProduct.unitPrice
                    .minus(orderProduct.unitPrice.times(it.discountValue).div(BigDecimal(100)))
                    .setDefaultScale()
            } else {
                orderProduct.unitPrice.minus(it.discountValue).setDefaultScale()
            }
        } ?: orderProduct.unitPrice.setDefaultScale()

        return orderProduct.apply {
            this.totalValue = orderProduct.unitPrice.multiply(orderProduct.units.toBigDecimal()).setDefaultScale()
            this.unitPriceWithDiscount = unitPriceWithDiscount
            this.totalValueWithDiscount = unitPriceWithDiscount.multiply(orderProduct.units.toBigDecimal()).setDefaultScale()
        }
    }

    override fun calculateTotalOrder(orderProducts: List<OrderProduct>, order: Order): Order {
        order.totalValue = orderProducts.sumOf { it.totalValue }
        order.totalValueWithDiscount = orderProducts.sumOf { it.totalValueWithDiscount }
        order.updatedAt = LocalDateTime.now()

        return order
    }
}