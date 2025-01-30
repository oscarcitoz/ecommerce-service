package com.fluxi.order.repositories

import com.fluxi.order.models.Order
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository("order")
interface OrderRepository : CrudRepository<Order, Long> {
}