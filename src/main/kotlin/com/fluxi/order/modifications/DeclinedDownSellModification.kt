package com.fluxi.order.modifications

import com.fluxi.offer.services.OfferCustomerServiceInterface
import com.fluxi.order.models.OrderModification
import com.fluxi.order.models.OrderModificationType
import jakarta.inject.Singleton

@Singleton
class DeclinedDownSellModification(
    private val offerCustomerServiceInterface: OfferCustomerServiceInterface,
) : BaseOrderModification {
    override fun makeModification(orderModification: OrderModification): OrderModification {
        this.offerCustomerServiceInterface.disabledOfferCustomer(orderModification.orderId)

        return orderModification
    }

    override fun getModificationType(): String {
        return OrderModificationType.DECLINED_DOWN_SELL
    }
}