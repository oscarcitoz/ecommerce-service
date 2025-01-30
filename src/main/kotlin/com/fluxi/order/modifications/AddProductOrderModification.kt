package com.fluxi.order.modifications

import com.fluxi.offer.models.OfferCustomer
import com.fluxi.offer.services.OfferCustomerServiceInterface
import com.fluxi.order.calculators.OrderTotalCalculatorInterface
import com.fluxi.order.models.OrderModification
import com.fluxi.order.models.OrderModificationType
import com.fluxi.order.models.OrderProduct
import com.fluxi.order.models.OrderProductOffer
import com.fluxi.order.repositories.OrderProductOfferRepository
import com.fluxi.order.repositories.OrderProductRepository
import com.fluxi.order.repositories.OrderRepository
import com.fluxi.store.models.Product
import com.fluxi.store.services.StoreServiceInterface
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton

@Singleton
class AddProductOrderModification(
    private val orderRepository: OrderRepository,
    private val orderProductRepository: OrderProductRepository,
    private val orderProductOfferRepository: OrderProductOfferRepository,
    private val storeServiceInterface: StoreServiceInterface,
    private val offerCustomerServiceInterface: OfferCustomerServiceInterface,
    private val orderTotalCalculatorInterface: OrderTotalCalculatorInterface,
) : BaseOrderModification {
    override fun makeModification(orderModification: OrderModification): OrderModification {
        val productId = orderModification.raw?.get("product_id") as String
        val units = orderModification.raw?.get("units") as? Int ?: 1
        val product = this.storeServiceInterface.findProductById(productId)
        val offerCustomer =
            this.offerCustomerServiceInterface.findByOrderIdActive(orderModification.orderId, product.id)
        val orderProduct = this.getOrderProduct(orderModification, units, product)

        offerCustomer?.let { this.createOrderProductOffer(orderProduct, offerCustomer) }

        this.orderProductRepository.save(
            this.orderTotalCalculatorInterface.calculateTotalProduct(
                orderProduct,
                offerCustomer
            )
        )

        this.orderRepository.update(
            this.orderTotalCalculatorInterface.calculateTotalOrder(
                this.orderProductRepository.findByOrderId(
                    orderModification.orderId
                ),
                this.orderRepository.findById(orderModification.orderId)
                    .orElseThrow { throw HttpStatusException(HttpStatus.BAD_REQUEST, "Order Not Exists") },
            )
        )

        return orderModification
    }

    private fun createOrderProductOffer(orderProduct: OrderProduct, offerCustomer: OfferCustomer) {
        this.orderProductOfferRepository.save(OrderProductOffer().apply {
            this.orderId = orderProduct.orderId
            this.offerId = offerCustomer.offerId
            this.orderProductId = orderProduct.id!!
            this.startsAt = offerCustomer.startsAt
            this.endsAt = offerCustomer.endsAt
            this.discountType = offerCustomer.discountType
            this.discountValue = offerCustomer.discountValue
        })
    }

    private fun getOrderProduct(orderModification: OrderModification, units: Int, product: Product): OrderProduct {
        return OrderProduct().apply {
            this.orderId = orderModification.orderId
            this.productId = product.id
            this.name = product.name
            this.images = product.images
            this.unitPrice = product.price
            this.units = units
        }
    }

    override fun getModificationType(): String {
        return OrderModificationType.ADD_PRODUCT
    }
}