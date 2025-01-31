package com.fluxi.order.repositories

import com.fluxi.order.models.OrderStore
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.Optional

@Repository("order")
interface OrderStoreRepository : CrudRepository<OrderStore, Long> {
    fun findByOrderId(orderId: Long): Optional<OrderStore>
}