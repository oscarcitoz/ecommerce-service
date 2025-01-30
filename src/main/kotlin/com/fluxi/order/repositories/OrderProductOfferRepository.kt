package com.fluxi.order.repositories

import com.fluxi.order.models.OrderProductOffer
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository("order")
interface OrderProductOfferRepository : CrudRepository<OrderProductOffer, Long> {
}