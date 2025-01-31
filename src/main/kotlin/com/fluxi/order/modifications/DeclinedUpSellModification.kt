package com.fluxi.order.modifications

import com.fluxi.offer.models.OfferCustomer
import com.fluxi.offer.services.OfferCustomerServiceInterface
import com.fluxi.offer.services.OfferServiceInterface
import com.fluxi.order.models.OrderModification
import com.fluxi.order.models.OrderModificationType
import com.fluxi.order.repositories.OrderStoreRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton
import java.time.LocalDateTime

@Singleton
class DeclinedUpSellModification(
    private val offerServiceInterface: OfferServiceInterface,
    private val orderStoreRepository: OrderStoreRepository,
    private val offerCustomerServiceInterface: OfferCustomerServiceInterface,
) : BaseOrderModification {
    override fun makeModification(orderModification: OrderModification): OrderModification {
        val orderStore = this.orderStoreRepository.findByOrderId(orderModification.orderId)
            .orElseThrow { throw HttpStatusException(HttpStatus.BAD_REQUEST, "Order Store Not Exists") }
        val offerCustomers = this.offerServiceInterface.findByOwnerId(orderStore.ownerId).map {
            OfferCustomer().apply {
                this.orderId = orderModification.orderId
                this.offerId = it.id!!
                this.productId = it.productId
                this.discountType = it.discountType
                this.discountValue = it.discountValue
                this.startsAt = LocalDateTime.now()
                this.endsAt = LocalDateTime.now().plusMinutes(15)
            }
        }

        this.offerCustomerServiceInterface.saveAll(offerCustomers)

        return orderModification
    }

    override fun getModificationType(): String {
        return OrderModificationType.DECLINED_UP_SELL
    }
}