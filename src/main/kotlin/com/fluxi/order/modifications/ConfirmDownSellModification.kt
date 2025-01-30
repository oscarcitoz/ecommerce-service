package com.fluxi.order.modifications

import com.fluxi.offer.services.OfferCustomerServiceInterface
import com.fluxi.order.calculators.OrderTotalCalculatorInterface
import com.fluxi.order.models.OrderModification
import com.fluxi.order.models.OrderModificationType
import com.fluxi.order.repositories.OrderProductOfferRepository
import com.fluxi.order.repositories.OrderProductRepository
import com.fluxi.order.repositories.OrderRepository
import com.fluxi.order.repositories.OrderStoreRepository
import com.fluxi.store.services.StoreServiceInterface
import jakarta.inject.Singleton

@Singleton
class ConfirmDownSellModification(
    orderRepository: OrderRepository,
    orderStoreRepository: OrderStoreRepository,
    orderProductRepository: OrderProductRepository,
    orderProductOfferRepository: OrderProductOfferRepository,
    storeServiceInterface: StoreServiceInterface,
    private val offerCustomerServiceInterface: OfferCustomerServiceInterface,
    orderTotalCalculatorInterface: OrderTotalCalculatorInterface
) : AddProductOrderModification(
    orderRepository, orderStoreRepository, orderProductRepository,
    orderProductOfferRepository, storeServiceInterface, offerCustomerServiceInterface, orderTotalCalculatorInterface
) {
    override fun makeModification(orderModification: OrderModification): OrderModification {
        super.makeModification(orderModification)
        this.offerCustomerServiceInterface.disabledOfferCustomer(orderModification.orderId)

        return orderModification
    }

    override fun getModificationType(): String {
        return OrderModificationType.CONFIRM_DOWN_SELL
    }
}