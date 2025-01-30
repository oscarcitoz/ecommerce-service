package com.fluxi.order.repositories

import com.fluxi.order.models.OrderCustomer
import com.fluxi.order.models.OrderStore
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository("order")
interface OrderStoreRepository : CrudRepository<OrderStore, Long> {
}